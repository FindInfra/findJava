package com.find.findcore.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "agency_subscription")
public class AgencySubscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date subscription_start_date;
	private Date subscription_end_date;
	@CreationTimestamp
	private Date created_date;
	private boolean isSubscribed;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "subscribed_agency", joinColumns = @JoinColumn(name = "agency_subscription_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agency_id", referencedColumnName = "id"))
	private Agency agency;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "subscribed_subscription_plan", joinColumns = @JoinColumn(name = "agency_subscription_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id"))
	private Subscription subscription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getSubscription_start_date() {
		return subscription_start_date;
	}

	public void setSubscription_start_date(Date subscription_start_date) {
		this.subscription_start_date = subscription_start_date;
	}

	public Date getSubscription_end_date() {
		return subscription_end_date;
	}

	public void setSubscription_end_date(Date subscription_end_date) {
		this.subscription_end_date = subscription_end_date;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public boolean isSubscribed() {
		return isSubscribed;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.isSubscribed = isSubscribed;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

}
