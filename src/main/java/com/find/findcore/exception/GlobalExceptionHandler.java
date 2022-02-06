package com.find.findcore.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorMessage> handleExpiredJwtException(ExpiredJwtException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
			WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(),
				ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RefreshTokenException.class)
	public ResponseEntity<ErrorMessage> handleTokenRefreshException(RefreshTokenException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN.value(), new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(),
				ex.getLocalizedMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}