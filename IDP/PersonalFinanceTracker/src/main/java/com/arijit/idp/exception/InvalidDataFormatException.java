package com.arijit.idp.exception;

public class InvalidDataFormatException extends Exception {
	
	private static final long serialVersionUID = 2871544965032608318L;
	private String message;

	public String getMessage() {
		return message;
	}

	public InvalidDataFormatException() {
		super();
	}

	public InvalidDataFormatException(String message) {
		super();
		this.message = message;
	}
}
