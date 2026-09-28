package com.tiffinservice.tiffin_service.billing;

import com.tiffinservice.tiffin_service.model.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;
    private LocalDate billDate;
    private BigDecimal subscriptionAmount;
    private BigDecimal extraTiffinAmount;
    private BigDecimal totalAmount;
    private BigDecimal paidAmount;
    private BigDecimal remainingAmount;

    private String paymentStatus;

    public Bill() {
    }

    public  BigDecimal getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public  BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount( BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public  BigDecimal getExtraTiffinAmount() {
        return extraTiffinAmount;
    }

    public void setExtraTiffinAmount( BigDecimal extraTiffinAmount) {
        this.extraTiffinAmount = extraTiffinAmount;
    }

    public void setSubscriptionAmount(BigDecimal subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public BigDecimal getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(BigDecimal paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Bill(Customer customer,
                LocalDate billDate,
                BigDecimal subscriptionAmount,
                BigDecimal extraTiffinAmount,
                BigDecimal totalAmount, BigDecimal paidAmount,
                BigDecimal remainingAmount,
                String paymentStatus) {

        this.customer = customer;
        this.billDate = billDate;
        this.subscriptionAmount = subscriptionAmount;
        this.extraTiffinAmount = extraTiffinAmount;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.remainingAmount = remainingAmount;
        this.paymentStatus = paymentStatus;
    }

}
