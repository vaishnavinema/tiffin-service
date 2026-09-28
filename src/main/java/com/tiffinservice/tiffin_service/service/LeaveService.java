package com.tiffinservice.tiffin_service.service;

import com.tiffinservice.tiffin_service.dto.LeaveRequest;
import com.tiffinservice.tiffin_service.exception.CustomerNotFoundException;
import com.tiffinservice.tiffin_service.exception.LeaveException;
import com.tiffinservice.tiffin_service.leave.Leave;
import com.tiffinservice.tiffin_service.model.Customer;
import com.tiffinservice.tiffin_service.repository.CustomerRepository;
import com.tiffinservice.tiffin_service.repository.LeaveRepository;
import com.tiffinservice.tiffin_service.repository.SubscriptionRepository;
import com.tiffinservice.tiffin_service.subscription.Subscription;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository repository;
    private final CustomerRepository customerRepository;
    private final SubscriptionRepository subscriptionRepository;

    public LeaveService(
            LeaveRepository repository,
            CustomerRepository customerRepository,
            SubscriptionRepository subscriptionRepository) {

        this.repository = repository;
        this.customerRepository = customerRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Leave saveLeave(LeaveRequest request) {

        if (request.getCustomerId() == null) {
            throw new LeaveException("Customer ID is required");
        }

        validateDates(
                request.getStartDate(),
                request.getEndDate()
        );

        Customer customer =
                customerRepository.findById(request.getCustomerId())
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer not found"
                                ));

        long leaveDays = calculateLeaveDays(
                request.getStartDate(),
                request.getEndDate()
        );

        Leave leave = new Leave();

        leave.setCustomer(customer);
        leave.setStartDate(request.getStartDate());
        leave.setEndDate(request.getEndDate());

        Leave savedLeave = repository.save(leave);

        // 4 or more days = subscription extension
        if (leaveDays >= 4) {

            Subscription subscription =
                    subscriptionRepository
                            .findTopByCustomerIdOrderByEndDateDesc(
                                    request.getCustomerId()
                            )
                            .orElseThrow(() ->
                                    new LeaveException(
                                            "Subscription not found"
                                    ));

            LocalDate newEndDate =
                    subscription.getEndDate()
                            .plusDays(leaveDays);

            subscription.setEndDate(newEndDate);

            subscriptionRepository.save(subscription);
        }

        return savedLeave;
    }

    public List<Leave> getAllLeaves() {
        return repository.findAll();
    }

    public long calculateLeaveDays(
            LocalDate startDate,
            LocalDate endDate) {

        return ChronoUnit.DAYS.between(
                startDate,
                endDate
        ) + 1;
    }

    private void validateDates(
            LocalDate startDate,
            LocalDate endDate) {

        if (startDate == null || endDate == null) {
            throw new LeaveException(
                    "Leave start date and end date are required"
            );
        }

        if (endDate.isBefore(startDate)) {
            throw new LeaveException(
                    "Leave end date cannot be before leave start date"
            );
        }
    }
}