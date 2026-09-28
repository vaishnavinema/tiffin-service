package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.billing.Bill;
import com.tiffinservice.tiffin_service.extra.ExtraTiffin;
import com.tiffinservice.tiffin_service.payment.Payment;
import com.tiffinservice.tiffin_service.repository.BillRepository;
import com.tiffinservice.tiffin_service.repository.ExtraTiffinRepository;
import com.tiffinservice.tiffin_service.repository.PaymentRepository;
import com.tiffinservice.tiffin_service.repository.SubscriptionRepository;
import com.tiffinservice.tiffin_service.subscription.Subscription;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ExtraTiffinRepository extraTiffinRepository;
    private final PaymentRepository paymentRepository;

    public BillService(
            BillRepository billRepository,
            SubscriptionRepository subscriptionRepository,
            ExtraTiffinRepository extraTiffinRepository,
            PaymentRepository paymentRepository) {

        this.billRepository = billRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.extraTiffinRepository = extraTiffinRepository;
        this.paymentRepository = paymentRepository;
    }

    public Bill generateBill(Long customerId) {

        Subscription subscription =
                subscriptionRepository
                        .findTopByCustomerIdOrderByEndDateDesc(customerId)
                        .orElseThrow(() ->
                                new RuntimeException("Subscription not found"));

        if (subscription.getMonthlyAmount() == null) {
            throw new RuntimeException(
                    "Monthly subscription amount is not set");
        }

        LocalDate billDate = LocalDate.now();

        LocalDate monthStart =
                billDate.withDayOfMonth(1);

        LocalDate monthEnd =
                billDate.withDayOfMonth(
                        billDate.lengthOfMonth()
                );

        // Prevent duplicate monthly bill
        List<Bill> existingBills =
                billRepository.findByCustomerIdAndBillDateBetween(
                        customerId,
                        monthStart,
                        monthEnd
                );

        if (!existingBills.isEmpty()) {
            throw new RuntimeException(
                    "Bill already generated for this month"
            );
        }

        // Get this month's extra tiffins
        List<ExtraTiffin> extraTiffins =
                extraTiffinRepository
                        .findByCustomerIdAndDateBetween(
                                customerId,
                                monthStart,
                                monthEnd
                        );

        BigDecimal extraTiffinAmount = BigDecimal.ZERO;

        for (ExtraTiffin extraTiffin : extraTiffins) {
            extraTiffinAmount =
                    extraTiffinAmount.add(extraTiffin.getTotalAmount());
        }

        BigDecimal subscriptionAmount =
                subscription.getMonthlyAmount();

        BigDecimal totalAmount =
                subscriptionAmount.add(extraTiffinAmount);

        // Get customer's payments
        List<Payment> payments =
                paymentRepository.findByCustomerId(customerId);

        BigDecimal paidAmount = BigDecimal.ZERO;

        for (Payment payment : payments) {

            if (payment.getPaymentDate() != null
                    && !payment.getPaymentDate().isBefore(monthStart)
                    && !payment.getPaymentDate().isAfter(monthEnd)) {

                paidAmount = paidAmount.add(payment.getAmount());
            }
        }

        if (paidAmount.compareTo(totalAmount) > 0) {
                throw new RuntimeException(
                        "Payment amount cannot be greater than bill amount");
            }

            BigDecimal remainingAmount =
                    totalAmount.subtract(paidAmount);

            String paymentStatus;

            if (paidAmount.compareTo(BigDecimal.ZERO) == 0) {
                paymentStatus = "UNPAID";
            } else if (paidAmount.compareTo(totalAmount) < 0) {
                paymentStatus = "PARTIAL";
            } else {
                paymentStatus = "PAID";
            }

            Bill bill = new Bill();

            bill.setCustomer(subscription.getCustomer());
            bill.setBillDate(billDate);
            bill.setSubscriptionAmount(subscriptionAmount);
            bill.setExtraTiffinAmount(extraTiffinAmount);
            bill.setTotalAmount(totalAmount);
            bill.setPaidAmount(paidAmount);
            bill.setRemainingAmount(remainingAmount);
            bill.setPaymentStatus(paymentStatus);

            return billRepository.save(bill);
        }

        public List<Bill> getAllBills() {
            return billRepository.findAll();
        }

        public Bill getBillById(Long id){

            return billRepository.findById(id)
                    .orElseThrow(() ->
                            new RuntimeException("Bill not found"));
        }
    }
