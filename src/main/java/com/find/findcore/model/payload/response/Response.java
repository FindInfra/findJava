package com.find.findcore.model.payload.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Response {

	public static final String FAILURE = "failure";
	public static final String SUCCESS = "success";

	@JsonInclude(value = Include.NON_NULL)
	private Object data;
	private HttpStatus status;
	private int statusCode;
	private String message;
	private String responseStatus;
	@JsonInclude(Include.NON_NULL)
	private String token;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public void markFailed(HttpStatus status, String message) {
		this.responseStatus = FAILURE;
		this.statusCode = status.value();
		this.status = status;
		this.message = message;
	}

	public void markSuccessful(String message) {
		this.responseStatus = SUCCESS;
		this.status = HttpStatus.OK;
		this.statusCode = HttpStatus.OK.value();
		this.message = message;
	}

}
