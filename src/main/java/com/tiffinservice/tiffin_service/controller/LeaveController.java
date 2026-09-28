package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.dto.LeaveRequest;
import com.tiffinservice.tiffin_service.leave.Leave;
import com.tiffinservice.tiffin_service.service.LeaveService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService service;

    public LeaveController(LeaveService service) {
        this.service = service;
    }

    @PostMapping
    public Leave addLeave(
           @Valid @RequestBody LeaveRequest request) {

        return service.saveLeave(request);
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return service.getAllLeaves();
    }
}