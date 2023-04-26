package com.arijit.idp.exception;

public class NotANumberException extends Exception {
	
	private static final long serialVersionUID = -4742804718073572845L;
	private String message;

	public String getMessage() {
		return message;
	}

	public NotANumberException() {
		super();
	}

	public NotANumberException(String message) {
		super();
		this.message = message;
	}
}
