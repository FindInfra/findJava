package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.entity.Agent;

public interface AgentService {

	Agent agentSignUp(Agent agent);

	void agentSignUpIn(Agent agent);

	Agent agentVerify(Agent agent);

	boolean agentExists(String mobileno);

	List<Agent> getAllAgents();

}
