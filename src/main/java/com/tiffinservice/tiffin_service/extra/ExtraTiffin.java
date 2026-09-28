package com.tiffinservice.tiffin_service.extra;

import com.tiffinservice.tiffin_service.model.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Date;
@Entity
public class ExtraTiffin {
 /*   ExtraTiffin
├── id
├── customer
├── date
├── quantity
└── totalAmount*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Customer customer;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than zero")
    private Integer quantity;

    private  BigDecimal totalAmount;

    public ExtraTiffin(){

    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount (BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public ExtraTiffin(Customer customer, LocalDate date, Integer quantity) {
         this.customer = customer;
         this.date = date;
         this.quantity = quantity;

     }

    public LocalDate getDate() {
        return date;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(long id) {
        this.id = id;
    }
}
