package com.find.findcore.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "property_address")
public class PropertyAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 50)
	private String flat_no;
	@Size(max = 50)
	private String floor;
	@Size(max = 150)
	private String building_name;
	@Size(max = 50)
	private String building_no;
	@Size(max = 150)
	private String name_of_street;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_propery_district", joinColumns = @JoinColumn(name = "property_address_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "district_id", referencedColumnName = "id"))
	private PropertyDistrict propery_district;

	public PropertyAddress() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFlat_no() {
		return flat_no;
	}

	public void setFlat_no(String flat_no) {
		this.flat_no = flat_no;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

	public String getBuilding_no() {
		return building_no;
	}

	public void setBuilding_no(String building_no) {
		this.building_no = building_no;
	}

	public String getName_of_street() {
		return name_of_street;
	}

	public void setName_of_street(String name_of_street) {
		this.name_of_street = name_of_street;
	}

	public PropertyDistrict getPropery_district() {
		return propery_district;
	}

	public void setPropery_district(PropertyDistrict propery_district) {
		this.propery_district = propery_district;
	}

}
