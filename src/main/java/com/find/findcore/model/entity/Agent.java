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
@Table(name = "agent")
public class Agent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(max = 150)
	private String fullname;
	@Size(max = 15)
	private String mobileno;
	@Size(max = 120)
	private String licenseno;
	@Size(max = 120)
	private String password;
	private boolean isEnabled = false;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_agency", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "agency_id", referencedColumnName = "id"))
	private Agency agency;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "agent_profiles", joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
	private AgentProfile profile;

	public Agent() {}
	
	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public AgentProfile getProfile() {
		return profile;
	}

	public void setProfile(AgentProfile profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "Agent [id=" + id + ", fullname=" + fullname + ", mobileno=" + mobileno + ", licenseno=" + licenseno
				+ ", password=" + password + ", isEnabled=" + isEnabled + ", agency=" + agency + ", profile=" + profile
				+ "]";
	}

}
