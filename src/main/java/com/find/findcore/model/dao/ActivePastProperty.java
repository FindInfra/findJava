package com.find.findcore.model.dao;

import com.find.findcore.model.entity.Property;

public class ActivePastProperty {

	private String views;
	private String star_rating;
	private Property property;

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getStar_rating() {
		return star_rating;
	}

	public void setStar_rating(String star_rating) {
		this.star_rating = star_rating;
	}

}
