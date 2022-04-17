package com.find.findcore.service;

import java.util.List;
import java.util.Optional;

import com.find.findcore.model.entity.Agency;

public interface AgencyService {
	
	public Agency saveAgency(Agency agency);

	public Optional<Agency> getAgencyById(Long id);

	public List<Agency> getAllAgencies();

	public List<Agency> saveAgencies(List<Agency> agencies);

}
