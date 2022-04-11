package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.find.findcore.model.entity.PropertyDesign;

public interface PropertyDesignRepository extends JpaRepository<PropertyDesign, Long> {
	@Modifying
	@Query(value = "TRUNCATE TABLE property_design RESTART IDENTITY;", nativeQuery = true)
	void truncateTable();
}
