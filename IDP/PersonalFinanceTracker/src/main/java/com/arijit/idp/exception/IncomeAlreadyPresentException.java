package com.arijit.idp.exception;

public class IncomeAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -2425402787219668782L;
	private String message;

	public String getMessage() {
		return message;
	}

	public IncomeAlreadyPresentException() {
		super();
	}

	public IncomeAlreadyPresentException(String message) {
		super();
		this.message = message;
	}
}
