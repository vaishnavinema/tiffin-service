package com.tiffinservice.tiffin_service.repository;

import com.tiffinservice.tiffin_service.extra.ExtraTiffin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExtraTiffinRepository extends JpaRepository<ExtraTiffin, Long> {
    List<ExtraTiffin> findByCustomerIdAndDateBetween(
            Long customerId,
            LocalDate startDate,
            LocalDate endDate
    );
}
