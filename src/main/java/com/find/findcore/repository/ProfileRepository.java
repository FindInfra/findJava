package com.find.findcore.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.find.findcore.model.entity.Profile;
import com.find.findcore.model.enumeration.Provider;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> {
	Profile findByEmailAndProvider(String emailId, Provider provider);
}