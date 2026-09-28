package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.repository.BillRepository;
import com.tiffinservice.tiffin_service.repository.CustomerRepository;
import com.tiffinservice.tiffin_service.repository.ExtraTiffinRepository;
import com.tiffinservice.tiffin_service.repository.PaymentRepository;
import com.tiffinservice.tiffin_service.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final ExtraTiffinRepository extraTiffinRepository;
    private final PaymentRepository paymentRepository;
    private final BillRepository billRepository;

    public DashboardService(
            CustomerRepository customerRepository,
            SubscriptionRepository subscriptionRepository,
            ExtraTiffinRepository extraTiffinRepository,
            PaymentRepository paymentRepository,
            BillRepository billRepository) {

        this.customerRepository = customerRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.extraTiffinRepository = extraTiffinRepository;
        this.paymentRepository = paymentRepository;
        this.billRepository = billRepository;
    }

    public Map<String, Object> getDashboard() {

        Map<String, Object> dashboard = new HashMap<>();

        dashboard.put(
                "totalCustomers",
                customerRepository.count()
        );

        dashboard.put(
                "activeCustomers",
                customerRepository.countByActiveTrue()
        );

        dashboard.put(
                "inactiveCustomers",
                customerRepository.countByActiveFalse()
        );

        dashboard.put(
                "totalSubscriptions",
                subscriptionRepository.count()
        );

        dashboard.put(
                "totalExtraTiffins",
                extraTiffinRepository.count()
        );

        dashboard.put(
                "totalPayments",
                paymentRepository.count()
        );

        dashboard.put(
                "totalBills",
                billRepository.count()
        );

        return dashboard;
    }
}