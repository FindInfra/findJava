package com.find.findcore.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.find.findcore.model.entity.Agent;

@Service
public class AgentAuthDetailsImpl implements UserDetails {

	private Long id;
	private String fullname;
	private String mobileno;
	private String licenseno;
	@JsonIgnore
	private String password;
	private boolean isEnabled;
	private Collection<? extends GrantedAuthority> authorities;

	public AgentAuthDetailsImpl() {
	}

	public AgentAuthDetailsImpl(Long id, String fullname, String mobileno, String licenseno, String password,
			boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.mobileno = mobileno;
		this.licenseno = licenseno;
		this.password = password;
		this.isEnabled = isEnabled;
		this.authorities = authorities;
	}

	public static AgentAuthDetailsImpl build(Agent agent) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		return new AgentAuthDetailsImpl(agent.getId(), agent.getFullname(), agent.getMobileno(), agent.getLicenseno(),
				agent.getPassword(), agent.isEnabled(), authorities);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getLicenseno() {
		return licenseno;
	}

	public void setLicenseno(String licenseno) {
		this.licenseno = licenseno;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return mobileno;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
