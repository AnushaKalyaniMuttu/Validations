package com.validate.logic.exception;

public class FiltersValidationException extends RuntimeException {

	
	 static final long serialVersionUID = 1L;
		private final String message;

	    public FiltersValidationException(String message) {
	        super();
	        this.message = message;
	}

		public String getMessage() {
			return message;
		}
}
