package com.arijit.idp.exception;

public class ExpenditureNotFoundException extends Exception {

	private String message;
	
	private static final long serialVersionUID = 3352775979640385562L;

	public ExpenditureNotFoundException() {
		super();
	}
	
	public ExpenditureNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}