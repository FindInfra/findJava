package com.find.findcore.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Override
	public Property addProperty(Property property) {
		try {
			return propertyRepository.save(property);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
	public Map<String,Object> getAllPropertyAdjectives() {
		Map<String,Object> objects = new HashMap<String, Object>();
		try {
			objects.put("Design",propertyDesignRepository.findAll());
			objects.put("Neighborhood",propertyNeighborhoodRepository.findAll());
			objects.put("Views",propertyViewsRepository.findAll());
			objects.put("Amenities",propertyAmenitiesRepository.findAll());
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
	public void deleteAllPropertyDistricts() {
		try {
			propertyDistrictsRepository.truncateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}