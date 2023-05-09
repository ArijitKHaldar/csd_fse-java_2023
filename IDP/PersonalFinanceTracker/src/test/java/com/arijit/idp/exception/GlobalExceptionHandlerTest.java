package com.arijit.idp.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

	@Mock
	private LoginAlreadyPresentException loginAlreadyPresentException;

	@Mock
	private LoginNotFoundException loginNotFoundException;

	@Mock
	private IncomeAlreadyPresentException incomeAlreadyPresentException;

	@Mock
	private IncomeNotFoundException incomeNotFoundException;

	@Mock
	private ExpenditureAlreadyPresentException expenditureAlreadyPresentException;

	@Mock
	private ExpenditureNotFoundException expenditureNotFoundException;

	@Mock
	private CategoryAlreadyPresentException categoryAlreadyPresentException;

	@Mock
	private CategoryNotFoundException categoryNotFoundException;

	@Mock
	private NullValueEnteredException nullValueEnteredException;

	@InjectMocks
	private GlobalExceptionHandler globalExceptionHandler;

	@Test
	void handleLoginAlreadyPresentExceptionTest() {
		String errorMessage = "User already registered with given email id";
		when(loginAlreadyPresentException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleLoginAlreadyPresentException(loginAlreadyPresentException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	void handleLoginNotFoundException() {
		String errorMessage = "User not found with given email id";
		when(loginNotFoundException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleLoginNotFoundException(loginNotFoundException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void handleIncomeAlreadyPresentExceptionTest() {
		String errorMessage = "Income already registered with given income id";
		when(incomeAlreadyPresentException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleIncomeAlreadyPresentException(incomeAlreadyPresentException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	void handleincomeNotFoundException() {
		String errorMessage = "Income not found";
		when(incomeNotFoundException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleIncomeNotFoundException(incomeNotFoundException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void handleExpenditureAlreadyPresentExceptionTest() {
		String errorMessage = "Expenditure already registered with given expenditure id";
		when(expenditureAlreadyPresentException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleExpenditureAlreadyPresentException(expenditureAlreadyPresentException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	void handleExpenditureNotFoundException() {
		String errorMessage = "Expenditure not found";
		when(expenditureNotFoundException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleExpenditureNotFoundException(expenditureNotFoundException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void handleCategoryAlreadyPresentExceptionTest() {
		String errorMessage = "Duplicate value of Category id entered";
		when(categoryAlreadyPresentException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleCategoryAlreadyPresentException(categoryAlreadyPresentException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
	}

	@Test
	void handleCategoryNotFoundException() {
		String errorMessage = "Category not found";
		when(categoryNotFoundException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleCategoryNotFoundException(categoryNotFoundException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
	}

	@Test
	void handleNullValueEnteredException() {
		String errorMessage = "Null Value entered";
		when(nullValueEnteredException.getMessage()).thenReturn(errorMessage);

		ResponseEntity<String> responseEntity = globalExceptionHandler
				.handleNullValueEnteredException(nullValueEnteredException);

		assertEquals(errorMessage, responseEntity.getBody());
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
	}
}
