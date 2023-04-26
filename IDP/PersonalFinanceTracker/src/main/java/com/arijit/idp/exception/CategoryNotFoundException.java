package com.arijit.idp.exception;

public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = -6275153581359672880L;
	private String message;

	public String getMessage() {
		return message;
	}

	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message) {
		super();
		this.message = message;
	}

}
