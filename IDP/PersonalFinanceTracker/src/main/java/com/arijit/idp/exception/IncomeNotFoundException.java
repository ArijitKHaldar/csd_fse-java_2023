package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncomeNotFoundException extends Exception {

	private static final long serialVersionUID = -2116026769404205145L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public IncomeNotFoundException() {
		super();
	}

	public IncomeNotFoundException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

}
