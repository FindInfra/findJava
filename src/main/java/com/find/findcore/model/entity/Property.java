package com.find.findcore.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "property")
public class Property {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 15)
	private String property_for;
	@Size(max = 15)
	private String tenancy;
	@Size(max = 15)
	private String type_of_property;
	private String describe_in_one_sentance;
	private String describe_in_one_paragraph;
	private String owner_testimonial;
	@Size(max = 15)
	private String net_area;
	@Size(max = 15)
	private String gross_area;
	@Size(max = 15)
	private String bedroom;
	@Size(max = 15)
	private String bathroom;
	@Size(max = 50)
	private String price;
	@Size(max = 50)
	private String landlord_rating;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"))
	private Agent agent;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property_address", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"))
	private PropertyAddress property_address;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property_views", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "views_id", referencedColumnName = "id"))
	private Set<PropertyViews> property_views;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property_design", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "design_id", referencedColumnName = "id"))
	private Set<PropertyDesign> property_design;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property_neighborhood", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "neighborhood_id", referencedColumnName = "id"))
	private Set<PropertyNeighborhood> property_neighborhoods;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_property_amenities", joinColumns = @JoinColumn(name = "propery_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id"))
	private Set<PropertyAmenities> property_amenities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNet_area() {
		return net_area;
	}

	public void setNet_area(String net_area) {
		this.net_area = net_area;
	}

	public String getGross_area() {
		return gross_area;
	}

	public void setGross_area(String gross_area) {
		this.gross_area = gross_area;
	}

	public String getBedroom() {
		return bedroom;
	}

	public void setBedroom(String bedroom) {
		this.bedroom = bedroom;
	}

	public String getBathroom() {
		return bathroom;
	}

	public void setBathroom(String bathroom) {
		this.bathroom = bathroom;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProperty_for() {
		return property_for;
	}

	public void setProperty_for(String property_for) {
		this.property_for = property_for;
	}

	public String getTenancy() {
		return tenancy;
	}

	public void setTenancy(String tenancy) {
		this.tenancy = tenancy;
	}

	public String getType_of_property() {
		return type_of_property;
	}

	public void setType_of_property(String type_of_property) {
		this.type_of_property = type_of_property;
	}

	public String getDescribe_in_one_sentance() {
		return describe_in_one_sentance;
	}

	public void setDescribe_in_one_sentance(String describe_in_one_sentance) {
		this.describe_in_one_sentance = describe_in_one_sentance;
	}

	public String getDescribe_in_one_paragraph() {
		return describe_in_one_paragraph;
	}

	public void setDescribe_in_one_paragraph(String describe_in_one_paragraph) {
		this.describe_in_one_paragraph = describe_in_one_paragraph;
	}

	public String getOwner_testimonial() {
		return owner_testimonial;
	}

	public void setOwner_testimonial(String owner_testimonial) {
		this.owner_testimonial = owner_testimonial;
	}

	public String getLandlord_rating() {
		return landlord_rating;
	}

	public void setLandlord_rating(String landlord_rating) {
		this.landlord_rating = landlord_rating;
	}

}
