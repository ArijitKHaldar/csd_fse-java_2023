package com.arijit.idp.helpermodules;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InputValidatorTest {

	@Test
	void testIsValidEmail_ValidEmail_ReturnsTrue() {
		String email = "test@example.com";
		assertTrue(InputValidator.isValidEmail(email));
	}

	@Test
	void testIsValidEmail_InvalidEmail_ReturnsFalse() {
		String email = "test@example";
		assertFalse(InputValidator.isValidEmail(email));
	}

	@Test
	void testIsValidPassword_ValidPassword_ReturnsTrue() {
		String password = "Test123!";
		assertTrue(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPassword_ReturnsFalse() {
		String password = "password";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordLength_ReturnsFalse() {
		String password = "Test123";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordNoLowerCase_ReturnsFalse() {
		String password = "TEST123!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordNoUpperCase_ReturnsFalse() {
		String password = "test123!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordNoDigit_ReturnsFalse() {
		String password = "TestTest!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordNoSpecialCharacter_ReturnsFalse() {
		String password = "Test123";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordStartsWithDigit_ReturnsFalse() {
		String password = "1Test123!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordStartsWithSpecialCharacter_ReturnsFalse() {
		String password = "!Test123!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	@Test
	void testIsValidPassword_InvalidPasswordContainsNullCharacter_ReturnsFalse() {
		String password = "Test\0123!";
		assertFalse(InputValidator.isValidPassword(password));
	}

	private static boolean isSpecialCharacter(char c) {
		return "!@#$%^&*()_+{}:\"<>?|[];',./`~\\-=".indexOf(c) != -1;
	}

	@Test
	void testIsSpecialCharacter() {
		char[] specialChars = { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '{', '}', ':', '"', '<',
				'>', '?', '|', '[', ']', ';', ',', '.', '/', '`', '~', '\\', '-', '=' };

		for (char c : specialChars) {
			assertTrue(isSpecialCharacter(c));
		}

		assertFalse(isSpecialCharacter('a'));
		assertFalse(isSpecialCharacter('1'));
		assertFalse(isSpecialCharacter(' '));
	}
}
