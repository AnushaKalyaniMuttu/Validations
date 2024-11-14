package com.validate.logic.exception;

public class LogicalExpressionMisMatchException extends RuntimeException {
 static final long serialVersionUID = 1L;
	private final String message;

    public LogicalExpressionMisMatchException(String message) {
        super();
        this.message = message;
}

	public String getMessage() {
		return message;
	}
}
