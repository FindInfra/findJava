package com.find.findcore.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	
}
