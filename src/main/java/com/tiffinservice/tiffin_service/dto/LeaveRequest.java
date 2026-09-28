package com.tiffinservice.tiffin_service.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class LeaveRequest {

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Leave start date is required")
    private LocalDate startDate;

    @NotNull(message = "Leave end date is required")
    private LocalDate endDate;

    public LeaveRequest() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}