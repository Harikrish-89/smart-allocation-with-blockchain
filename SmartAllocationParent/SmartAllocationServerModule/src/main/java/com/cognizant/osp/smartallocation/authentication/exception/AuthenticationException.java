package com.cognizant.osp.smartallocation.authentication.exception;

public class AuthenticationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message) {
		super(message);
	}

	public AuthenticationException(String message, Throwable e) {
		super(message, e);
	}

	public AuthenticationException(Throwable e) {
		super(e);
	}
}
