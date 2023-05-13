package com.arijit.idp.exception;

public class NotAStringException extends Exception {

	private static final long serialVersionUID = -6273694327885588240L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public NotAStringException() {
		super();
	}

	public NotAStringException(String message) {
		super();
		this.message = message;
	}
}
