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

import com.find.findcore.model.entity.Property;
import com.find.findcore.model.payload.response.Response;
import com.find.findcore.service.PropertyService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/find")
public class PropertyController {
	private static final Logger log = LoggerFactory.getLogger(PropertyController.class);

	@Autowired
	PropertyService propertyService;

	@PostMapping({ "/add-property" })
	public Response addProperty(@RequestBody Property property) {
		Response response = new Response();
		try {
			response.markSuccessful("Property Added.");
			response.setData(propertyService.addProperty(property));
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}

		return response;

	};

	@GetMapping({ "/property" })
	public Response getProperty(@RequestBody Property property) {
		Response response = new Response();
		try {
			response.markSuccessful("Property Fetched.");
			response.setData(propertyService.getPropertyById(property));
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;

		}

	}

	@GetMapping({ "/properties" })
	public Response getAllProperties() {
		Response response = new Response();
		try {
			response.markSuccessful("Properties Fetched.");
			response.setData(propertyService.getProperties());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-all-properties" })
	public Response deleteAllProperties() {
		Response response = new Response();
		try {
			response.markSuccessful("All Properties Deleted.");
			propertyService.deleteAllProperties();
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@PostMapping({ "/delete-property" })
	public Response deleteProperty(@RequestBody Property property) {
		Response response = new Response();
		try {
			response.markSuccessful("Property removed!");
			propertyService.deletePropertyById(property);

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@PostMapping({ "/update-property" })
	public Response updateProperty(@RequestBody Property property) {
		Response response = new Response();
		try {
			response.markSuccessful("Property updated!");
			response.setData(propertyService.updateProperty(property));

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@PostMapping({ "/add-property-views" })
	public Response addPropertyViews() {
		Response response = new Response();
		try {
			response.markSuccessful("Property view added!");
			propertyService.addPropertyViews();

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@GetMapping({ "/property-views" })
	public Response getAllPropertyViews() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Views Fetched.");
			response.setData(propertyService.getPropertyViews());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-property-views" })
	public Response deleteAllPropertyViews() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Views deleted.");
			propertyService.deleteAllPropertyViews();
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}
	
	@PostMapping({ "/add-property-design" })
	public Response addPropertyDesign() {
		Response response = new Response();
		try {
			response.markSuccessful("Property design added!");
			propertyService.addPropertyDesign();

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@GetMapping({ "/property-design" })
	public Response getAllPropertyDesign() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Design Fetched.");
			response.setData(propertyService.getPropertyDesign());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-property-design" })
	public Response deleteAllPropertyDesign() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Design deleted.");
			propertyService.deleteAllPropertyDesign();
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@PostMapping({ "/add-property-neighborhood" })
	public Response addPropertyNeighborhood() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Neighborhood added!");
			propertyService.addPropertyNeighborhood();

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@GetMapping({ "/property-neighborhood" })
	public Response getAllPropertyNeighborhood() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Neighborhood Fetched.");
			response.setData(propertyService.getPropertyNeighborhood());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-property-neighborhood" })
	public Response deleteAllPropertyNeighborhood() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Neighborhood deleted.");
			propertyService.deleteAllPropertyNeighborhood();
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}
	
	@PostMapping({ "/add-property-amenities" })
	public Response addPropertyAmenities() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Amenities added!");
			propertyService.addPropertyAmenities();

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
		return response;
	}

	@GetMapping({ "/property-amenities" })
	public Response getPropertyAmenities() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Amenities Fetched.");
			response.setData(propertyService.getPropertyAmenities());
			return response;

		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}

	@GetMapping({ "/delete-property-amenities" })
	public Response deleteAllPropertyAmenities() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Amenities deleted.");
			propertyService.deleteAllPropertyAmenities();
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}
	
	@GetMapping({ "/property-adjectives" })
	public Response getAllPropertyAdjectives() {
		Response response = new Response();
		try {
			response.markSuccessful("Property Adjectives Fetched.");
			response.setData(propertyService.getAllPropertyAdjectives());
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response.markFailed(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
			return response;
		}
	}
}