package com.arijit.idp.exception;

public class NullValueEnteredException extends Exception {

	private static final long serialVersionUID = 1962152111621963732L;
	private String message;
	public String getMessage() {
		return message;
	}
	public NullValueEnteredException() {
		super();
	}
	public NullValueEnteredException(String message) {
		super();
		this.message = message;
	}
}
