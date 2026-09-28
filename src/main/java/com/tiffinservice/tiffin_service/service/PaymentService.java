package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.dto.PaymentRequest;
import com.tiffinservice.tiffin_service.exception.CustomerNotFoundException;
import com.tiffinservice.tiffin_service.model.Customer;
import com.tiffinservice.tiffin_service.payment.Payment;
import com.tiffinservice.tiffin_service.repository.CustomerRepository;
import com.tiffinservice.tiffin_service.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {

    private final PaymentRepository repository;
    private final CustomerRepository customerRepository;

    public PaymentService(
            PaymentRepository repository,
            CustomerRepository customerRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    public Payment savePayment(PaymentRequest request) {

        if (request.getCustomerId() == null) {
            throw new RuntimeException("Customer ID is required");
        }

        if (request.getPaymentDate() == null) {
            throw new RuntimeException("Payment date is required");
        }

        if (request.getAmount() == null
                || request.getAmount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Payment amount must be greater than zero"
            );
        }

        if (request.getPaymentMode() == null
                || request.getPaymentMode().isBlank()) {

            throw new RuntimeException(
                    "Payment mode is required"
            );
        }

        Customer customer =
                customerRepository.findById(request.getCustomerId())
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found"
                                ));

        Payment payment = new Payment();

        payment.setCustomer(customer);
        payment.setPaymentDate(request.getPaymentDate());
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());

        return repository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public List<Payment> getPaymentsByCustomer(
            Long customerId) {

        return repository.findByCustomerId(customerId);
    }
}