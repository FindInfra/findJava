package com.find.findcore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Size(max = 150)
	private String title_name;
	@Size(max = 150)
	private String title_description;
	private String subs_description;
	@Size(max = 150)
	private String description_heading;
	@Size(max = 150)
	private String no_of_listing;
	@Size(max = 150)
	private String amount;
	@Size(max = 150)
	private String per_listing_amount;
	private boolean enable = false;

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(long id, @Size(max = 150) String title_name, @Size(max = 150) String title_description,
			String subs_description, @Size(max = 150) String description_heading, @Size(max = 150) String no_of_listing,
			@Size(max = 150) String amount, @Size(max = 150) String per_listing_amount, boolean enable) {
		super();
		this.id = id;
		this.title_name = title_name;
		this.title_description = title_description;
		this.subs_description = subs_description;
		this.description_heading = description_heading;
		this.no_of_listing = no_of_listing;
		this.amount = amount;
		this.per_listing_amount = per_listing_amount;
		this.enable = enable;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle_name() {
		return title_name;
	}

	public void setTitle_name(String title_name) {
		this.title_name = title_name;
	}

	public String getTitle_description() {
		return title_description;
	}

	public void setTitle_description(String title_description) {
		this.title_description = title_description;
	}

	public String getNo_of_listing() {
		return no_of_listing;
	}

	public void setNo_of_listing(String no_of_listing) {
		this.no_of_listing = no_of_listing;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPer_listing_amount() {
		return per_listing_amount;
	}

	public void setPer_listing_amount(String per_listing_amount) {
		this.per_listing_amount = per_listing_amount;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getSubs_description() {
		return subs_description;
	}

	public void setSubs_description(String subs_description) {
		this.subs_description = subs_description;
	}

	public String getDescription_heading() {
		return description_heading;
	}

	public void setDescription_heading(String description_heading) {
		this.description_heading = description_heading;
	}

}