package com.find.findcore.model.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class RefreshTokenRequest {
	@NotBlank
	private String refreshToken;
}