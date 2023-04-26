package com.arijit.idp.exception;

public class ExpenditureAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -5791550362194789504L;
	private String message;

	public ExpenditureAlreadyPresentException() {
		super();
	}

	public ExpenditureAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
