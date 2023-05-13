package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -7063088689992217812L;

	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public LoginAlreadyPresentException() {
		super();
	}

	public LoginAlreadyPresentException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

}
