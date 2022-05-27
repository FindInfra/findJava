package com.find.findcore.model.dao;

import com.find.findcore.model.entity.Agent;

public class AgentWithDetails {

	private Agent agent;
	private String views;
	private String rating;
	private String propertylisted;

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getViews() {
		return views;
	}

	public void setViews(String views) {
		this.views = views;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getPropertylisted() {
		return propertylisted;
	}

	public void setPropertylisted(String propertylisted) {
		this.propertylisted = propertylisted;
	}

	@Override
	public String toString() {
		return "AgentWithDetails [agent=" + agent + ", views=" + views + ", rating=" + rating + ", propertylisted="
				+ propertylisted + "]";
	}

}
