package com.cognizant.osp.smartallocation.rest.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cognizant.osp.smartallocation.authentication.exception.AuthenticationException;
import com.cognizant.osp.smartallocation.oabcs.exception.AllocationServiceException;

@RestControllerAdvice
public class SmartAllocationRestServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { AllocationServiceException.class })
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR,
				request);
	}

	@ExceptionHandler(value = { AuthenticationException.class })
	protected ResponseEntity<Object> handleAuthorizationException(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = ex.getMessage();
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}
}