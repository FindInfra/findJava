package com.find.findcore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agent_connection")
public class AgentConnection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String agentMob;
	private String connectionMob;

	public AgentConnection() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAgentMob() {
		return agentMob;
	}

	public void setAgentMob(String agentMob) {
		this.agentMob = agentMob;
	}

	public String getConnectionMob() {
		return connectionMob;
	}

	public void setConnectionMob(String connectionMob) {
		this.connectionMob = connectionMob;
	}

}