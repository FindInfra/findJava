package com.find.findcore.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.AgentProfile;
import com.find.findcore.repository.AgentProfileRepository;
import com.find.findcore.repository.AgentRepository;
import com.find.findcore.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService, UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	AgentProfileRepository agentProfileRepository;

	@Override
	public Agent agentSignUp(Agent agent) {
		return agentRepository.save(agent);
	}

	@Override
	public Agent agentSignIn(Agent agent) {
		return agentRepository.findByMobilenoAndIsEnabled(agent.getMobileno(), true);
	}

	@Override
	public Agent agentVerify(Agent agent) {
		agent = agentRepository.findByMobileno(agent.getMobileno());
		agent.setEnabled(true);
		return agentRepository.save(agent);
	}

	@Override
	public Agent agentSubscribe(Agent agent) {
		agent = agentRepository.getById(agent.getId());
		agent.setSubscribed(true);
		return agentRepository.save(agent);
	}

	@Override
	public boolean agentExists(String mobileno) {
		return agentRepository.existsByMobileno(mobileno);
	}

	@Override
	public boolean enableAgentExists(String mobileno) {
		return agentRepository.existsByMobilenoAndIsEnabled(mobileno, true);
	}

	@Override
	public Agent getEnableAgentByMobile(String mobileno) {
		return agentRepository.findByMobilenoAndIsEnabled(mobileno, true);
	}

	@Override
	public Agent getAgentByMobile(String mobileno) {
		return agentRepository.findByMobileno(mobileno);
	}

	@Override
	public Agent getAgentById(Long id) {
		return agentRepository.getById(id);
	}

	@Override
	public Agent getEnableAgentById(Long id) {
		return agentRepository.getByIdAndAndIsEnabled(id, true);
	}

	@Override
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mobileno) throws RuntimeException {
		Agent agent = agentRepository.findByMobilenoAndIsEnabled(mobileno, true);
		log.info(agent.toString());
		return AgentAuthDetailsImpl.build(agent);
	}

	@Override
	public void deleteAgent(String mobileno) {
		agentRepository.delete(agentRepository.findByMobileno(mobileno));
	}

	@Override
	public AgentProfile getAgentProfileByMobileno(String mobileno) {
		Agent agent = agentRepository.findByMobileno(mobileno);
		return agent.getProfile();
	}

	@Override
	public void changeAvatar(AgentProfile agentProfile, String mobileno) {

		Agent agent = agentRepository.findByMobileno(mobileno);
		AgentProfile profile = agent.getProfile();
		profile.setAvatarImage(agentProfile.getAvatarImage());
		agent.setProfile(profile);
		agentRepository.save(agent);
	}

	@Override
	public Agent updateProfile(AgentProfile agentProfile, String mobileno) {
		
		Agent agent = agentRepository.findByMobileno(mobileno);
		AgentProfile profile = agent.getProfile();

		profile.setFullName(agentProfile.getFullName());
		profile.setMobileno(agentProfile.getMobileno());
		profile.setVideoUrl(agentProfile.getVideoUrl());

		profile = agentProfileRepository.save(profile);
		agent.setProfile(profile);
		agent.setMobileno(agentProfile.getMobileno());
		agent.setFullname(agentProfile.getFullName());

		return agentRepository.save(agent);
	}

	@Override
	public AgentProfile saveProfile(AgentProfile agentProfile) {
		return agentProfileRepository.save(agentProfile);
	}

}
