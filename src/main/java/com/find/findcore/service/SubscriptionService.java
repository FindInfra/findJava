package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.entity.Subscription;

public interface SubscriptionService {

	public List<Subscription> getAllSubscriptions();

	public List<Subscription> saveSubscriptions(List<Subscription> subscriptions);

	public Subscription saveSubscription(Subscription subscription);

	public void deleteAllSubscriptions();


}
