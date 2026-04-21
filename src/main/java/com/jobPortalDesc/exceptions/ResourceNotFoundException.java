package com.jobPortalDesc.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	//to tell problem
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
