package com.find.findcore.model.dao;

import java.util.List;

public class ActivePastPropertiesWrapper {

	private String avialable_listing;
	private String subs_days;
	private List<ActivePastProperty> activeProperties;
	private List<ActivePastProperty> pastProperties;

	public String getAvialable_listing() {
		return avialable_listing;
	}

	public void setAvialable_listing(String avialable_listing) {
		this.avialable_listing = avialable_listing;
	}

	public String getSubs_days() {
		return subs_days;
	}

	public void setSubs_days(String subs_days) {
		this.subs_days = subs_days;
	}

	public List<ActivePastProperty> getActiveProperties() {
		return activeProperties;
	}

	public void setActiveProperties(List<ActivePastProperty> activeProperties) {
		this.activeProperties = activeProperties;
	}

	public List<ActivePastProperty> getPastProperties() {
		return pastProperties;
	}

	public void setPastProperties(List<ActivePastProperty> pastProperties) {
		this.pastProperties = pastProperties;
	}

}
