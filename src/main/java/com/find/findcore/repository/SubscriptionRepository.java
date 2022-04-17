package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.find.findcore.model.entity.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}
