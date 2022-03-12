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
import com.find.findcore.repository.AgentRepository;
import com.find.findcore.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService, UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

	@Autowired
	AgentRepository agentRepository;

	@Override
	public Agent agentSignUp(Agent agent) {
		return agentRepository.save(agent);
	}

	@Override
	public Agent agentSignIn(Agent agent) {
		return agentRepository.findByMobileno(agent.getMobileno());
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
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String mobileno) throws RuntimeException {
		Agent agent = agentRepository.findByMobileno(mobileno);
		log.info(agent.toString());
		return AgentAuthDetailsImpl.build(agent);
	}
}
