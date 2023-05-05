package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncomeAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -2425402787219668782L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public IncomeAlreadyPresentException() {
		super();
	}

	public IncomeAlreadyPresentException(String message) {
		super();
		this.message = message;
		log.error(message);
	}
}
