package com.find.findcore.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.find.findcore.model.dao.ActivePastPropertiesWrapper;
import com.find.findcore.model.dao.ActivePastProperty;
import com.find.findcore.model.entity.AgencySubscription;
import com.find.findcore.model.entity.Agent;
import com.find.findcore.model.entity.Property;
import com.find.findcore.model.entity.PropertyAddress;
import com.find.findcore.model.entity.PropertyAmenities;
import com.find.findcore.model.entity.PropertyDesign;
import com.find.findcore.model.entity.PropertyDistrict;
import com.find.findcore.model.entity.PropertyNeighborhood;
import com.find.findcore.model.entity.PropertyViews;
import com.find.findcore.model.enumeration.EPropertyAmenities;
import com.find.findcore.model.enumeration.EPropertyDesign;
import com.find.findcore.model.enumeration.EPropertyDistricts;
import com.find.findcore.model.enumeration.EPropertyNeighborhood;
import com.find.findcore.model.enumeration.EPropertyViews;
import com.find.findcore.repository.PropertyAddressRepository;
import com.find.findcore.repository.PropertyAmenitiesRepository;
import com.find.findcore.repository.PropertyDesignRepository;
import com.find.findcore.repository.PropertyDistrictsRepository;
import com.find.findcore.repository.PropertyNeighborhoodRepository;
import com.find.findcore.repository.PropertyRepository;
import com.find.findcore.repository.PropertyViewsRepository;
import com.find.findcore.security.jwt.JwtUtils;
import com.find.findcore.service.AgentService;
import com.find.findcore.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	PropertyViewsRepository propertyViewsRepository;

	@Autowired
	PropertyNeighborhoodRepository propertyNeighborhoodRepository;

	@Autowired
	PropertyDesignRepository propertyDesignRepository;

	@Autowired
	PropertyAmenitiesRepository propertyAmenitiesRepository;

	@Autowired
	PropertyAddressRepository propertyAddressRepository;

	@Autowired
	PropertyDistrictsRepository propertyDistrictsRepository;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	AgentService agentService;

	@Override
	public Property addProperty(Property property, String token) {
		try {

			String mobileno = jwtUtils.getUserNameFromJwtToken(token);
			Agent agent = agentService.getAgentByMobile(mobileno);
			if (agent != null) {
				PropertyAddress address = property.getProperty_address();
				address = addPropertyAddress(address);
				property.setProperty_address(address);
				property.setAgent(agent);
				return propertyRepository.save(property);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Property getPropertyById(Property property) {
		try {
			return propertyRepository.getById(property.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Property> getProperties() {
		try {
			return propertyRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ActivePastPropertiesWrapper getAllActivePropertiesOfAgent(String token) {
		try {
			ActivePastPropertiesWrapper activePastPropertiesWrapper = new ActivePastPropertiesWrapper();
			List<ActivePastProperty> activeProperties = new ArrayList<ActivePastProperty>();
			AgencySubscription agencySubscription = agentService.checkAgencySubscription(token);
			String mobileno = jwtUtils.getUserNameFromJwtToken(token);
			Agent agent = agentService.getAgentByMobile(mobileno);
			List<Property> properties = propertyRepository.findByAgent(agent);
			for (Property property : properties) {
				ActivePastProperty activePastProperty = new ActivePastProperty();
				activePastProperty.setStar_rating("1.2");
				activePastProperty.setViews("1.3");
				activePastProperty.setProperty(property);
				activeProperties.add(activePastProperty);
			}
			activePastPropertiesWrapper.setAvialable_listing(Integer.toString(30 - activeProperties.size()));

			if (agencySubscription.isSubscribed()) {

				activePastPropertiesWrapper.setActiveProperties(activeProperties);
				activePastPropertiesWrapper.setPastProperties(new ArrayList<ActivePastProperty>());
				activePastPropertiesWrapper
						.setSubs_days(Long.toString((agencySubscription.getSubscription_end_date().getTime()
								- Calendar.getInstance().getTimeInMillis()) / (1000 * 60 * 60 * 24)));
				return activePastPropertiesWrapper;
			} else {
				activePastPropertiesWrapper.setActiveProperties(new ArrayList<ActivePastProperty>());
				activePastPropertiesWrapper.setPastProperties(activeProperties);
				activePastPropertiesWrapper.setSubs_days("0");
				return activePastPropertiesWrapper;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteAllProperties() {
		try {
			propertyRepository.deleteAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deletePropertyById(Property property) {
		try {
			propertyRepository.delete(property);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Property updateProperty(Property property) {
		try {
			return propertyRepository.save(property);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<PropertyViews> getPropertyViews() {
		try {
			return propertyViewsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addPropertyViews() {
		try {
			List<PropertyViews> list = new ArrayList<PropertyViews>();
			for (EPropertyViews ePropertyViews : EPropertyViews.values()) {
				PropertyViews propertyViews = new PropertyViews();
				propertyViews.setPropertyViews(ePropertyViews);
				list.add(propertyViews);
			}
			propertyViewsRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteAllPropertyViews() {
		try {
			propertyViewsRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PropertyNeighborhood> getPropertyNeighborhood() {
		try {
			return propertyNeighborhoodRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addPropertyNeighborhood() {
		try {
			List<PropertyNeighborhood> list = new ArrayList<PropertyNeighborhood>();
			for (EPropertyNeighborhood ePropertyNeighborhood : EPropertyNeighborhood.values()) {
				PropertyNeighborhood propertyNeighborhood = new PropertyNeighborhood();
				propertyNeighborhood.setPropertyNeighborhood(ePropertyNeighborhood);
				list.add(propertyNeighborhood);
			}
			propertyNeighborhoodRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void deleteAllPropertyNeighborhood() {
		try {
			propertyNeighborhoodRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPropertyDesign() {
		try {
			List<PropertyDesign> list = new ArrayList<PropertyDesign>();
			for (EPropertyDesign ePropertyDesign : EPropertyDesign.values()) {
				PropertyDesign propertyDesign = new PropertyDesign();
				propertyDesign.setePropertyDesign(ePropertyDesign);
				list.add(propertyDesign);
			}
			propertyDesignRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PropertyDesign> getPropertyDesign() {
		try {
			return propertyDesignRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public void deleteAllPropertyDesign() {
		try {
			propertyDesignRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addPropertyAmenities() {
		try {
			List<PropertyAmenities> list = new ArrayList<PropertyAmenities>();
			for (EPropertyAmenities ePropertyAmenities : EPropertyAmenities.values()) {
				PropertyAmenities propertyAmenities = new PropertyAmenities();
				propertyAmenities.setPropertyAmenities(ePropertyAmenities);
				list.add(propertyAmenities);
			}
			propertyAmenitiesRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PropertyAmenities> getPropertyAmenities() {
		try {
			return propertyAmenitiesRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public void deleteAllPropertyAmenities() {
		try {
			propertyAmenitiesRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public PropertyAddress addPropertyAddress(PropertyAddress address) {
		try {
			return propertyAddressRepository.save(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PropertyAddress> getPropertyAddress() {
		try {
			return propertyAddressRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	@Transactional
	public void deletePropertyAddress(PropertyAddress address) {
		try {
			propertyAddressRepository.delete(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> getAllPropertyAdjectives() {
		Map<String, Object> objects = new HashMap<String, Object>();
		try {
			objects.put("Design", propertyDesignRepository.findAll());
			objects.put("Neighborhood", propertyNeighborhoodRepository.findAll());
			objects.put("Views", propertyViewsRepository.findAll());
			objects.put("Amenities", propertyAmenitiesRepository.findAll());
			objects.put("Districts", propertyDistrictsRepository.findAll());
			return objects;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void addPropertyDistricts() {
		try {
			List<PropertyDistrict> list = new ArrayList<PropertyDistrict>();
			for (EPropertyDistricts ePropertyDistricts : EPropertyDistricts.values()) {
				PropertyDistrict propertyDistricts = new PropertyDistrict();
				propertyDistricts.setePropertyDistricts(ePropertyDistricts);
				list.add(propertyDistricts);
			}
			propertyDistrictsRepository.saveAll(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<PropertyDistrict> getPropertyDistricts() {
		try {
			return propertyDistrictsRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public void deleteAllPropertyDistricts() {
		try {
			propertyDistrictsRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}