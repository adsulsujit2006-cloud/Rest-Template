package com.movie_services.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.movie_services.dto.response.ErrorResponse;


@RestControllerAdvice
public class GlobalExceptionHandler {

	  @ExceptionHandler(ResourceNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex
	            ,HttpServletRequest request){
	        ErrorResponse response = ErrorResponse.builder()
	                .timestmp(LocalDateTime.now())
	                .status(HttpStatus.CONFLICT.value())
	                .error(HttpStatus.CONFLICT.getReasonPhrase())
	                .message(ex.getMessage())
	                .path(request.getRequestURI())
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	    }
	  
	  @ExceptionHandler(NullPointerException.class)
	  public ResponseEntity<ErrorResponse> handleNullValue(NullPointerException ex,HttpServletRequest request){
		  ErrorResponse response = ErrorResponse.builder()
	                .timestmp(LocalDateTime.now())
	                .status(HttpStatus.CONFLICT.value())
	                .error(HttpStatus.CONFLICT.getReasonPhrase())
	                .message(ex.getMessage())
	                .path(request.getRequestURI())
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	  
	  
	  @ExceptionHandler(IllegalArgumentException.class)
	  public ResponseEntity<ErrorResponse> handleIllegalArg(IllegalArgumentException ex,HttpServletRequest request){
		  ErrorResponse response = ErrorResponse.builder()
	                .timestmp(LocalDateTime.now())
	                .status(HttpStatus.CONFLICT.value())
	                .error(HttpStatus.CONFLICT.getReasonPhrase())
	                .message(ex.getMessage())
	                .path(request.getRequestURI())
	                .build();

	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	  }
	 

