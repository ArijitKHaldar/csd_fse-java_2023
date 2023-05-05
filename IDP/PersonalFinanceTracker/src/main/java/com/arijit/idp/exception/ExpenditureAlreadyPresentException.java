package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpenditureAlreadyPresentException extends Exception {

	private static final long serialVersionUID = -5791550362194789504L;
	private String message;

	public ExpenditureAlreadyPresentException() {
		super();
	}

	public ExpenditureAlreadyPresentException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
