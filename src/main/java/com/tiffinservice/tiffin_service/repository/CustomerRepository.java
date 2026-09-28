package com.tiffinservice.tiffin_service.repository;

import com.tiffinservice.tiffin_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    long countByActiveTrue();

    long countByActiveFalse();
}
