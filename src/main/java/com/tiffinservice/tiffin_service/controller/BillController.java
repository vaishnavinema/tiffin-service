package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.billing.Bill;
import com.tiffinservice.tiffin_service.config.JwtService;
import com.tiffinservice.tiffin_service.service.BillService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    private final BillService service;

    public BillController(BillService service) {
        this.service = service;
    }

    @PostMapping("/{customerId}")
    public Bill generateBill(
            @PathVariable Long customerId) {

        return service.generateBill(customerId);
    }

    @GetMapping
    public List<Bill> getAllBills() {
        return service.getAllBills();
    }

    @GetMapping("/{id}")
    public Bill getBillById(
            @PathVariable Long id) {

        return service.getBillById(id);
    }

    }