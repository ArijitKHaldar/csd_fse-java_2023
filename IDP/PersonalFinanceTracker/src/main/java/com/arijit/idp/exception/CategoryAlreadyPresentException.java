package com.arijit.idp.exception;

public class CategoryAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 3849179403283857188L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public CategoryAlreadyPresentException() {
		super();
	}

	public CategoryAlreadyPresentException(String message) {
		super();
		this.message = message;
	}

}
