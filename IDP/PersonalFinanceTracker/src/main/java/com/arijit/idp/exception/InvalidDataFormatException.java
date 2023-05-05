package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvalidDataFormatException extends Exception {

	private static final long serialVersionUID = 2871544965032608318L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public InvalidDataFormatException() {
		super();
	}

	public InvalidDataFormatException(String message) {
		super();
		this.message = message;
		log.error(message);
	}
}
