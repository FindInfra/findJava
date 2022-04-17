package com.find.findcore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.find.findcore.model.entity.Subscription;
import com.find.findcore.model.payload.response.Response;
import com.find.findcore.service.SubscriptionService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class SubscriptionController {
	private static final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

	@Autowired
	SubscriptionService subscriptionService;

	@PostMapping({ "/add-subscription" })
	public Response addSubscriptions(@RequestBody Subscription subscription) {
		Response response = new Response();

		try {
			response.markSuccessful("Subscription Added.");
			response.setData(subscriptionService.saveSubscription(subscription));
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	};
	
	@PostMapping({ "/add-subscriptions" })
	public Response addSubscriptions(@RequestBody List<Subscription> subscriptions) {
		Response response = new Response();

		try {
			response.markSuccessful("Subscriptions Added.");
			response.setData(subscriptionService.saveSubscriptions(subscriptions));
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	};
	
	@GetMapping({ "/subscriptions" })
	public Response getAllSubscriptions() {
		Response response = new Response();

		try {
			response.markSuccessful("Subscription Fetched.");
			response.setData(subscriptionService.getAllSubscriptions());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-subscriptions" })
	public Response deleteAllSubscriptions() {
		Response response = new Response();

		try {
			response.markSuccessful("All Subscription Deleted.");
			subscriptionService.deleteAllSubscriptions();
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

}
