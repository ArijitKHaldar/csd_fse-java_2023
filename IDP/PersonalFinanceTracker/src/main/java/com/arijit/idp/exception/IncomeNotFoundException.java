package com.arijit.idp.exception;

public class IncomeNotFoundException extends Exception {
	
	private static final long serialVersionUID = -2116026769404205145L;
	private String message;

	public String getMessage() {
		return message;
	}

	public IncomeNotFoundException() {
		super();
	}

	public IncomeNotFoundException(String message) {
		super();
		this.message = message;
	}

}
