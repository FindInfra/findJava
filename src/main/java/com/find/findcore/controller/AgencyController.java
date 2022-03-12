package com.find.findcore.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.find.findcore.model.entity.Agency;
import com.find.findcore.model.payload.response.Response;
import com.find.findcore.service.AgencyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class AgencyController {

	private static final Logger log = LoggerFactory.getLogger(AgencyController.class);

	@Autowired
	AgencyService agencyService;

	@PostMapping({ "/add-agency" })
	public Response addAgency(@RequestBody Agency agency) {
		Response response = new Response();

		try {
			response.markSuccessful("Agency Added.");
			response.setData(agencyService.saveAgency(agency));
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	};

	@GetMapping({ "/agencies" })
	public Response getAllAgencies() {
		Response response = new Response();

		try {
			response.markSuccessful("Agency Fetched.");
			response.setData(agencyService.getAllAgencies());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	};
}
