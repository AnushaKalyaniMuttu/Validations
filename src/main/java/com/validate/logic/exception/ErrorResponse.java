package com.validate.logic.exception;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
  
    private int status;

    public ErrorResponse(int status,String message) {
        this.message = message;
       
        this.status = status;
    }

    // Getters and setters
}
