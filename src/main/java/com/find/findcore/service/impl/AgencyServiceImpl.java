package com.find.findcore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.find.findcore.model.entity.Agency;
import com.find.findcore.repository.AgencyRepository;
import com.find.findcore.service.AgencyService;

@Service
public class AgencyServiceImpl implements AgencyService {

	@Autowired
	AgencyRepository agencyRepository;
	
	@Override
	public Optional<Agency> getAgencyById(Long id) {
		return agencyRepository.findById(id);
	}

	@Override
	public List<Agency> getAllAgencies() {
		return agencyRepository.findAll();
	}

	@Override
	public Agency saveAgency(Agency agency) {
		return agencyRepository.save(agency);
	}

}
