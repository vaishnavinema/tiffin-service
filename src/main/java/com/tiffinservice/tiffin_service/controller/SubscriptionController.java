package com.tiffinservice.tiffin_service.controller;

import com.tiffinservice.tiffin_service.dto.SubscriptionRequest;
import com.tiffinservice.tiffin_service.subscription.Subscription;
import com.tiffinservice.tiffin_service.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService service;

    public SubscriptionController(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping
    public Subscription addSubscription(
            @Valid @RequestBody SubscriptionRequest request) {

        return service.saveSubscription(request);
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return service.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public Subscription getSubscriptionById(
            @PathVariable Long id) {

        return service.getSubscriptionById(id);
    }

    @PutMapping("/{id}")
    public Subscription updateSubscription(
            @PathVariable Long id,
            @Valid @RequestBody SubscriptionRequest request) {

        return service.updateSubscription(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscription(
            @PathVariable Long id) {

        service.deleteSubscription(id);
    }
}