package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.find.findcore.model.entity.PropertyAmenities;

public interface PropertyAmenitiesRepository extends JpaRepository<PropertyAmenities, Long> {
	@Modifying
	@Query(value = "TRUNCATE TABLE property_amenities RESTART IDENTITY;", nativeQuery = true)
	void truncateTable();

}
