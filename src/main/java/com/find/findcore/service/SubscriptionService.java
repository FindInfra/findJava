package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.entity.Subscription;

public interface SubscriptionService {

	public List<Subscription> getAllSubscriptions();

	public Subscription saveSubscriptions(Subscription subscription);

}
