package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.find.findcore.model.entity.AgentProfile;

public interface AgentProfileRepository extends JpaRepository<AgentProfile, Long> {

	AgentProfile findByMobileno(String mobileno);

	void deleteByMobileno(String mobileno);

}
