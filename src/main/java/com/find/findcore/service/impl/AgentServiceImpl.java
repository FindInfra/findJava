package com.find.findcore.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void saveProfile(AgentProfile agentProfile) {
		agentProfileRepository.save(agentProfile);
	}

	@Override
	public AgentProfile getAgentProfileByMobileno(String mobileno) {
		return agentProfileRepository.findByMobileno(mobileno);
	}
	
	@Override
	public void deleteProfile(String mobileno) {
		agentProfileRepository.deleteAll();
	}

}
