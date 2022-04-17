package com.find.findcore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.findcore.model.entity.Subscription;
import com.find.findcore.repository.SubscriptionRepository;
import com.find.findcore.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Override
	public List<Subscription> getAllSubscriptions() {
		return subscriptionRepository.findAll();
	}

	@Override
	public Subscription saveSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}

	@Override
	public List<Subscription> saveSubscriptions(List<Subscription> subscriptions) {
		return subscriptionRepository.saveAll(subscriptions);
	}

	@Override
	public void deleteAllSubscriptions() {
		subscriptionRepository.deleteAll();
	}

}
