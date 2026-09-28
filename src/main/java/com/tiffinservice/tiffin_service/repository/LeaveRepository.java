package com.tiffinservice.tiffin_service.repository;

import com.tiffinservice.tiffin_service.leave.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
}
