package com.validate.logic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LogicalExpressionMisMatchException.class)
    public ResponseEntity<ErrorResponse> handleLogicalExpressionMisMatchException(LogicalExpressionMisMatchException ex) {
      
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(FiltersValidationException.class)
    public ResponseEntity<ErrorResponse> handleFiltersValidationException(FiltersValidationException ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
