package com.arijit.idp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = LoginAlreadyPresentException.class)
	public ResponseEntity<String> handleLoginAlreadyPresentException(LoginAlreadyPresentException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "User already registered with given email id";
		}

		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = LoginNotFoundException.class)
	public ResponseEntity<String> handleLoginNotFoundException(LoginNotFoundException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Login details not found";
		}

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = IncomeAlreadyPresentException.class)
	public ResponseEntity<String> handleIncomeAlreadyPresentException(IncomeAlreadyPresentException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Duplicate value of Income id entered";
		}

		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = IncomeNotFoundException.class)
	public ResponseEntity<String> handleIncomeNotFoundException(IncomeNotFoundException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Income details not found";
		}

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ExpenditureAlreadyPresentException.class)
	public ResponseEntity<String> handleExpenditureAlreadyPresentException(ExpenditureAlreadyPresentException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Duplicate value of Expenditure id entered";
		}

		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = ExpenditureNotFoundException.class)
	public ResponseEntity<String> handleExpenditureNotFoundException(ExpenditureNotFoundException ex) {
		String message = ex.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Expenditure details not found";
		}

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = CategoryAlreadyPresentException.class)
	public ResponseEntity<String> handleCategoryAlreadyPresentException(CategoryAlreadyPresentException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Duplicate value of Category id entered";
		}

		return new ResponseEntity<String>(message, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = CategoryNotFoundException.class)
	public ResponseEntity<String> handleCategoryNotFoundException(CategoryNotFoundException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "Category details not found";
		}

		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = NotANumberException.class)
	public ResponseEntity<String> handleNotANumberException(NotANumberException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The value entered is not a number";
		}

		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NotAStringException.class)
	public ResponseEntity<String> handleNotAStringException(NotAStringException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The value entered is not a String";
		}

		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = NullValueEnteredException.class)
	public ResponseEntity<String> handleNullValueEnteredException(NullValueEnteredException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The value entered is empty";
		}

		return new ResponseEntity<String>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@ExceptionHandler(value = InvalidDataFormatException.class)
	public ResponseEntity<String> handleInvalidDataFormatException(InvalidDataFormatException exception) {
		String message = exception.getMessage();
		if (message == null || message.isEmpty()) {
			message = "The entered value does not match expected input";
		}

		return new ResponseEntity<String>(message, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}