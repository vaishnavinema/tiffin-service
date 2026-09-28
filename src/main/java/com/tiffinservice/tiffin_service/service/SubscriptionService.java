package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.dto.SubscriptionRequest;
import com.tiffinservice.tiffin_service.exception.CustomerNotFoundException;
import com.tiffinservice.tiffin_service.exception.SubscriptionNotFoundException;
import com.tiffinservice.tiffin_service.model.Customer;
import com.tiffinservice.tiffin_service.repository.CustomerRepository;
import com.tiffinservice.tiffin_service.repository.SubscriptionRepository;
import com.tiffinservice.tiffin_service.subscription.Subscription;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository repository;
    private final CustomerRepository customerRepository;

    public SubscriptionService(
            SubscriptionRepository repository,
            CustomerRepository customerRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    public Subscription saveSubscription(
            SubscriptionRequest request) {

        validateDates(
                request.getStartDate(),
                request.getEndDate()
        );

        if (request.getMonthlyAmount() == null
                || request.getMonthlyAmount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Monthly amount must be greater than zero"
            );
        }

        Customer customer =
                customerRepository.findById(request.getCustomerId())
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found"
                                ));

        Subscription subscription = new Subscription();

        subscription.setCustomer(customer);
        subscription.setStartDate(request.getStartDate());
        subscription.setEndDate(request.getEndDate());
        subscription.setMonthlyAmount(request.getMonthlyAmount());

        return repository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return repository.findAll();
    }

    public Subscription getSubscriptionById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new SubscriptionNotFoundException(
                                "Subscription not found"
                        ));
    }

    public Subscription updateSubscription(
            Long id,
            SubscriptionRequest request) {

        Subscription existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new SubscriptionNotFoundException(
                                        "Subscription not found"
                                ));

        validateDates(
                request.getStartDate(),
                request.getEndDate()
        );

         if(request.getMonthlyAmount() == null
                || request.getMonthlyAmount().compareTo(BigDecimal.ZERO) <= 0) {

            throw new RuntimeException(
                    "Monthly amount must be greater than zero"
            );
        }

        existing.setStartDate(request.getStartDate());
        existing.setEndDate(request.getEndDate());
        existing.setMonthlyAmount(request.getMonthlyAmount());

        return repository.save(existing);
    }

    public void deleteSubscription(Long id) {

        Subscription existing =
                repository.findById(id)
                        .orElseThrow(() ->
                                new SubscriptionNotFoundException(
                                        "Subscription not found"
                                ));

        repository.delete(existing);
    }

    private void validateDates(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new RuntimeException(
                    "Start date and end date are required"
            );
        }

        if (endDate.isBefore(startDate)) {
            throw new RuntimeException(
                    "End date cannot be before start date"
            );
        }
    }
}