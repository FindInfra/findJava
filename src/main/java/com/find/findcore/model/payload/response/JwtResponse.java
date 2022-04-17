package com.find.findcore.model.payload.response;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	// private String refreshToken;
	private Long id;
	private String username;
	private String email;
	private String provider;
	private List<String> roles;
	private String mobileno;

	public JwtResponse(String accessToken, Long id, String username, String email, String provider,
			List<String> roles) {
		this.token = accessToken;
		// this.refreshToken = refreshToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.provider = provider;
		this.roles = roles;
	}

	public JwtResponse(String token, Long id, String mobileno) {
		super();
		this.token = token;
		this.id = id;
		this.mobileno = mobileno;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

}