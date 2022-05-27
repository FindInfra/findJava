package com.find.findcore.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.find.findcore.model.dao.AgentWithDetails;
import com.find.findcore.model.entity.Agency;
import com.find.findcore.model.entity.AgencySubscription;
import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.AgentConnection;
import com.find.findcore.model.entity.AgentProfile;
import com.find.findcore.model.entity.Subscription;
import com.find.findcore.repository.AgencySubscriptionRepository;
import com.find.findcore.repository.AgentConnectionRepository;
import com.find.findcore.repository.AgentProfileRepository;
import com.find.findcore.repository.AgentRepository;
import com.find.findcore.repository.SubscriptionRepository;
import com.find.findcore.security.jwt.JwtUtils;
import com.find.findcore.service.AgentService;

@Service
public class AgentServiceImpl implements AgentService, UserDetailsService {
	private static final Logger log = LoggerFactory.getLogger(AgentServiceImpl.class);

	@Autowired
	AgentRepository agentRepository;

	@Autowired
	AgentProfileRepository agentProfileRepository;

	@Autowired
	AgencySubscriptionRepository agencySubscriptionRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AgentService agentService;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Autowired
	AgentConnectionRepository agentConnectionRepository;

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
	public AgencySubscription agentSubscribe(Subscription subscription, String token) {

		Calendar cal = Calendar.getInstance();

		String mobileno = jwtUtils.getUserNameFromJwtToken(token);
		Agent agent = agentService.getAgentByMobile(mobileno);

		Agency agency = agent.getAgency();
		subscription = subscriptionRepository.getById(subscription.getId());

		AgencySubscription agencySubscription = new AgencySubscription();
		agencySubscription.setAgency(agency);
		agencySubscription.setSubscription(subscription);
		agencySubscription.setSubscription_start_date(new Date());
		cal.add(Calendar.DATE, +30);
		agencySubscription.setSubscription_end_date(cal.getTime());
		agencySubscription.setSubscribed(true);

		return agencySubscriptionRepository.save(agencySubscription);
	}

	@Override
	public AgencySubscription getAgencySubscription(String token) {

		String mobileno = jwtUtils.getUserNameFromJwtToken(token);
		Agent agent = agentService.getAgentByMobile(mobileno);

		Agency agency = agent.getAgency();
		List<AgencySubscription> agencySubscriptions = agencySubscriptionRepository.findByAgency(agency);

		return agencySubscriptions.get(0);
	}

	@Override
	public AgencySubscription checkAgencySubscription(String token) {

		Calendar cal = Calendar.getInstance();
		String mobileno = jwtUtils.getUserNameFromJwtToken(token);
		Agent agent = agentService.getAgentByMobile(mobileno);

		Agency agency = agent.getAgency();
		List<AgencySubscription> agencySubscriptions = agencySubscriptionRepository.findByAgency(agency);
		AgencySubscription agencySubscription = agencySubscriptions.get(0);
		if (cal.getTimeInMillis() > agencySubscription.getSubscription_end_date().getTime()) {
			agencySubscription.setSubscribed(false);
			agencySubscription = agencySubscriptionRepository.save(agencySubscription);
			return agencySubscription;
		} else
			return agencySubscription;
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

	@Override
	public List<AgentWithDetails> getAgentConnections(String token) {

		List<AgentWithDetails> agentWithDetailss = new ArrayList<AgentWithDetails>();
		String agentmob = jwtUtils.getUserNameFromJwtToken(token);

		AgentConnection agentConnection = agentConnectionRepository.findByAgentMob(agentmob);
		String list[] = agentConnection.getConnectionMob().split(",");

		if (list.length > 0) {
			List<String> ids = Stream.of(list).map(String::valueOf).collect(Collectors.toList());
			List<Agent> agents = agentRepository.findAllByMobilenoIn(ids);
			for (Agent agent : agents) {
				AgentWithDetails agentWithDetails = new AgentWithDetails();
				agentWithDetails.setAgent(agent);
				agentWithDetails.setViews("1.3");
				agentWithDetails.setRating("1.2");
				agentWithDetails.setPropertylisted("10");
				agentWithDetailss.add(agentWithDetails);
			}
		}
		return agentWithDetailss;
	}

	@Override
	public void addAgentConnection(String mob, String agentmob) {

		AgentConnection agentConnection = agentConnectionRepository.findByAgentMob(agentmob);
		if (agentConnection == null) {
			agentConnection = new AgentConnection();
			agentConnection.setConnectionMob(mob);
		} else if (agentConnection.getConnectionMob().isEmpty()) {
			agentConnection.setConnectionMob(mob);
		} else {
			mob = agentConnection.getConnectionMob() + "," + mob;
			agentConnection.setConnectionMob(mob);
		}
		agentConnection.setAgentMob(agentmob);
		agentConnectionRepository.save(agentConnection);
	}

	@Override
	public boolean checkAgentConnection(String mob, String agentmob) {

		AgentConnection agentConnection = agentConnectionRepository.findByAgentMob(agentmob);
		if (agentConnection != null && agentConnection.getConnectionMob().contains(mob))
			return false;
		else
			return true;
	}
}