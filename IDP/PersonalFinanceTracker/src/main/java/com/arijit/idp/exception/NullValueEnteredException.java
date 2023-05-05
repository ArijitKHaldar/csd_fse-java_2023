package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NullValueEnteredException extends Exception {

	private static final long serialVersionUID = 1962152111621963732L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public NullValueEnteredException() {
		super();
	}

	public NullValueEnteredException(String message) {
		super();
		this.message = message;
		log.error(message);
	}
}
