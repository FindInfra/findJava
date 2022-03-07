package com.find.findcore.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.find.findcore.model.entity.Agency;
import com.find.findcore.service.AgencyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class AgencyController {

	private static final Logger log = LoggerFactory.getLogger(AgencyController.class);

	@Autowired
	AgencyService agencyService;
	
	@PostMapping({"/add-agencies"})
	public Agency addAgency(@RequestBody Agency agency){
		
		try {
			return agencyService.saveAgency(agency);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	};
	
	@GetMapping({"/agencies"})
	public List<Agency> getAllAgencies(){
		
		try {
			return agencyService.getAllAgencies();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	};
}
