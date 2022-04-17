package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.find.findcore.model.entity.Agent;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {

	public Boolean existsByMobileno(String mobileno);

	public Boolean existsByMobilenoAndIsEnabled(String mobileno, boolean isEnable);

	public Agent findByMobilenoAndIsEnabled(String mobileno, boolean isEnable);

	public Agent findByMobileno(String mobileno);

	public Agent getByIdAndAndIsEnabled(Long id, boolean isEnable);

}
