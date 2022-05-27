package com.find.findcore.service;

import java.util.List;

import com.find.findcore.model.dao.AgentWithDetails;
import com.find.findcore.model.entity.AgencySubscription;
import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.AgentProfile;
import com.find.findcore.model.entity.Subscription;

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

	AgentProfile getAgentProfileByMobileno(String mobileno);

	AgencySubscription agentSubscribe(Subscription subscription, String token);

	void changeAvatar(AgentProfile agentProfile, String mobileno);

	Agent updateProfile(AgentProfile agentProfile, String mobileno);

	AgentProfile saveProfile(AgentProfile agentProfile);

	AgencySubscription getAgencySubscription(String token);

	AgencySubscription checkAgencySubscription(String token);

	List<AgentWithDetails> getAgentConnections(String token);

	void addAgentConnection(String mob, String token);

	boolean checkAgentConnection(String mob, String token);

}
