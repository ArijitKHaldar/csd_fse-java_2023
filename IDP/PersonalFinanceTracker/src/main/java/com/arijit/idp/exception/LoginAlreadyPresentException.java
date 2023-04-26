package com.arijit.idp.exception;

public class LoginAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -7063088689992217812L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public LoginAlreadyPresentException() {
		super();
	}

	public LoginAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

}
