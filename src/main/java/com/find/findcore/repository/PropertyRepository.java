package com.find.findcore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.find.findcore.model.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

}
