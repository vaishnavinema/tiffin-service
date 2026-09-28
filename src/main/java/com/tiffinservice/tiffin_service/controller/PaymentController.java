package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.dto.PaymentRequest;
import com.tiffinservice.tiffin_service.payment.Payment;
import com.tiffinservice.tiffin_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping
    public Payment addPayment(
            @Valid @RequestBody PaymentRequest request) {

        return service.savePayment(request);
    }

    @GetMapping
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/customer/{customerId}")
    public List<Payment> getPaymentsByCustomer(
            @PathVariable Long customerId) {

        return service.getPaymentsByCustomer(customerId);
    }
}