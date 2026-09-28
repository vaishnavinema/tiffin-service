package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public Map<String, Object> getDashboard() {
        return service.getDashboard();
    }
}