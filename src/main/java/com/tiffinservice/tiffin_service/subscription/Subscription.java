package com.tiffinservice.tiffin_service.subscription;

import com.tiffinservice.tiffin_service.model.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal monthlyAmount;

    public Subscription() {
    }

    public Subscription(Customer customer, LocalDate startDate, LocalDate endDate, BigDecimal monthlyAmount) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.monthlyAmount = monthlyAmount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getMonthlyAmount() {
        return monthlyAmount;
    }

    public void setMonthlyAmount(BigDecimal monthlyAmount) {
        this.monthlyAmount = monthlyAmount;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}