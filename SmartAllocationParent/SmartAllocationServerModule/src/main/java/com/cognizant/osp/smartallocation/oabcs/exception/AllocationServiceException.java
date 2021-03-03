package com.cognizant.osp.smartallocation.oabcs.exception;

public class AllocationServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AllocationServiceException(String message) {
		super(message);
	}

	public AllocationServiceException(String message, Throwable e) {
		super(message, e);
	}

	public AllocationServiceException(Throwable e) {
		super(e);
	}
}
