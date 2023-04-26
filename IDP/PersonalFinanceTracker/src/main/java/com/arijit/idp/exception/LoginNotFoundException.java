package com.arijit.idp.exception;

public class LoginNotFoundException extends Exception {
	
	private static final long serialVersionUID = 4689948914452042286L;
	private String message;

	public LoginNotFoundException() {
		super();
	}

	public LoginNotFoundException(String message) {
		super();
		this.message = message;
		
	}

	public String getMessage() {
		return message;
	}
}
