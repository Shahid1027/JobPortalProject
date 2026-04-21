package com.jobPortalDesc.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex){
		ErrorResponse error = new ErrorResponse(404, ex.getMessage());
		return ResponseEntity.status(404).body(error);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRunTimeException(RuntimeException ex){
		ErrorResponse error = new ErrorResponse(400, ex.getMessage());
		return ResponseEntity.status(400).body(error);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex){
		ErrorResponse error = new ErrorResponse(500, "Got an Error");
		return ResponseEntity.status(500).body(error);
	}

}
