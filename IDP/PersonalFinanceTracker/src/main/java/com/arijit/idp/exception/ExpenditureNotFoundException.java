package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExpenditureNotFoundException extends Exception {

	private String message;

	private static final long serialVersionUID = 3352775979640385562L;

	public ExpenditureNotFoundException() {
		super();
	}

	public ExpenditureNotFoundException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

	@Override
	public String getMessage() {
		return message;
	}

}