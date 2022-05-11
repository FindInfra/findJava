package com.find.findcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.find.findcore.model.entity.Agency;
import com.find.findcore.model.entity.AgencySubscription;

public interface AgencySubscriptionRepository extends JpaRepository<AgencySubscription, Long> {

	List<AgencySubscription> findByAgency(Agency agency);

}
