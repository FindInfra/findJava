package com.find.findcore.service;

import java.util.List;
import java.util.Map;

import com.find.findcore.model.entity.Property;
import com.find.findcore.model.entity.PropertyAddress;
import com.find.findcore.model.entity.PropertyAmenities;
import com.find.findcore.model.entity.PropertyDesign;
import com.find.findcore.model.entity.PropertyNeighborhood;
import com.find.findcore.model.entity.PropertyViews;

public interface PropertyService {

	Property addProperty(Property property);

	Property getPropertyById(Property property);

	List<Property> getProperties();

	void deleteAllProperties();

	void deletePropertyById(Property property);

	Property updateProperty(Property property);

	List<PropertyViews> getPropertyViews();

	void addPropertyViews();

	void deleteAllPropertyViews();

	List<PropertyNeighborhood> getPropertyNeighborhood();

	void addPropertyNeighborhood();

	void deleteAllPropertyNeighborhood();

	void addPropertyDesign();

	List<PropertyDesign> getPropertyDesign();

	void deleteAllPropertyDesign();

	void addPropertyAmenities();

	void deleteAllPropertyAmenities();

	List<PropertyAmenities> getPropertyAmenities();
	
	PropertyAddress addPropertyAddress(PropertyAddress address);

	List<PropertyAddress> getPropertyAddress();

	void deletePropertyAddress(PropertyAddress address);

	Map<String, Object> getAllPropertyAdjectives();

}