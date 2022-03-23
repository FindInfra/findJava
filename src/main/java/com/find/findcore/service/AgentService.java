package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.AgentProfile;

public interface AgentService {

	Agent agentSignUp(Agent agent);

	Agent agentSignIn(Agent agent);

	Agent agentVerify(Agent agent);

	boolean agentExists(String mobileno);

	boolean enableAgentExists(String mobileno);

	List<Agent> getAllAgents();

	Agent getAgentByMobile(String mobileno);

	Agent getEnableAgentByMobile(String mobileno);

	Agent getAgentById(Long id);

	Agent getEnableAgentById(Long id);

	void deleteAgent(String mobileno);

	void saveProfile(AgentProfile agentProfile);

	AgentProfile getAgentProfileByMobileno(String mobileno);

	void deleteProfile(String mobileno);

}
