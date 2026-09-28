package com.tiffinservice.tiffin_service.repository;

import com.tiffinservice.tiffin_service.subscription.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findTopByCustomerIdOrderByEndDateDesc(Long customerId);
}
