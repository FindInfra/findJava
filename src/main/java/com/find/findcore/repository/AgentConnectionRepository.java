package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.find.findcore.model.entity.AgentConnection;

public interface AgentConnectionRepository extends JpaRepository<AgentConnection, Long> {

	AgentConnection findByAgentMob(String agentmob);

}
