package com.tiffinservice.tiffin_service.leave;

import com.tiffinservice.tiffin_service.model.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "customer_leave")
public class Leave {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
    @ManyToOne
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    public Leave() {
    }
    public Leave(Customer customer, LocalDate startDate, LocalDate endDate) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Long getId() {
        return id;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Long id) {
        this.id = id;
    }


}