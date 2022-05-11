package com.find.findcore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

	List<Property> findByAgent(Agent agent);

}
