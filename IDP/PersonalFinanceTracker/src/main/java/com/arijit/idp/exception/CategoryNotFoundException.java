package com.arijit.idp.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryNotFoundException extends Exception {

	private static final long serialVersionUID = -6275153581359672880L;
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public CategoryNotFoundException() {
		super();
	}

	public CategoryNotFoundException(String message) {
		super();
		this.message = message;
		log.error(message);
	}

}
