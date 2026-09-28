package com.tiffinservice.tiffin_service.repository;

import com.tiffinservice.tiffin_service.billing.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByCustomerIdAndBillDateBetween(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate
    );
}