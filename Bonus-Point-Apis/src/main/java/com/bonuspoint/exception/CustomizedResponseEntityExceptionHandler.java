package com.bonuspoint.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bonuspoint.util.ErrorCodes;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(),
	        request.getDescription(false), ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	@Override
	  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	      HttpHeaders headers, HttpStatus status, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
	        ex.getBindingResult().toString(), ErrorCodes.VALIDATION_FAILED.getCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	  } 

	@ExceptionHandler(ResourceNotFoundException.class)
	  public final ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false), ErrorCodes.RESOURCE_NOT_FOUND.getCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(CustomErrorException.class)
	  public final ResponseEntity<Object> handleCustomErrorException(CustomErrorException ex, WebRequest request) {
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false), ex.getErrorCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.BAD_REQUEST);
	  }
	
	@ExceptionHandler(FileStorageException.class)
	  public final ResponseEntity<Object> handleFileStorageException(FileStorageException ex, WebRequest request) {
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false), ErrorCodes.INTERNAL_SERVER_ERROR.getCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	@ExceptionHandler(MyFileNotFoundException.class)
	  public final ResponseEntity<Object> handleMyFileNotFoundException(MyFileNotFoundException ex, WebRequest request) {
		 ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false), ErrorCodes.RESOURCE_NOT_FOUND.getCode());
	    return new ResponseEntity<Object>(errorDetails, HttpStatus.OK);
	  }
	
}
