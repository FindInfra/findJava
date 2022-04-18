package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.find.findcore.model.entity.PropertyDistrict;

public interface PropertyDistrictsRepository extends JpaRepository<PropertyDistrict, Long> {

	@Modifying
	@Query(value = "TRUNCATE TABLE property_district RESTART IDENTITY;", nativeQuery = true)
	void truncateTable();
}
