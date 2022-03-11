package com.find.findcore.model.payload.response;

import org.springframework.http.HttpStatus;

public class Response {

	public static final String FAILURE = "failure";
	public static final String SUCCESS = "success";

	private Object data;
	protected HttpStatus status;
	protected String message;
	protected String responseStatus;
	protected String jwt;

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

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public void markFailed(HttpStatus status, String message) {
		this.responseStatus = FAILURE;
		this.status = status;
		this.message = message;
	}

	public void markSuccessful(String message) {
		this.responseStatus = SUCCESS;
		this.status = HttpStatus.OK;
		this.message = message;
	}

}
