package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.find.findcore.model.entity.PropertyNeighborhood;

public interface PropertyNeighborhoodRepository extends JpaRepository<PropertyNeighborhood, Long> {

	@Modifying
	@Query(value = "TRUNCATE TABLE property_neighborhood RESTART IDENTITY;", nativeQuery = true)
	void truncateTable();
}
