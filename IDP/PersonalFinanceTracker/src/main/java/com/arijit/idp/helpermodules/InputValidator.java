package com.arijit.idp.helpermodules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

	public static boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	public static boolean isValidPassword(String password) {
		// Check length
		if (password.length() < 8) {
			return false;
		}

		// Check first character
		char firstChar = password.charAt(0);
		if (Character.isDigit(firstChar) || isSpecialCharacter(firstChar)) {
			return false;
		}

		// Check for at least one lower case letter, one upper case letter, one digit,
		// and one special character
		boolean hasLowercase = false;
		boolean hasUppercase = false;
		boolean hasDigit = false;
		boolean hasSpecial = false;
		for (int i = 0; i < password.length(); i++) {
			char c = password.charAt(i);
			if (Character.isLowerCase(c)) {
				hasLowercase = true;
			} else if (Character.isUpperCase(c)) {
				hasUppercase = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else if (isSpecialCharacter(c)) {
				hasSpecial = true;
			}
		}

		return hasLowercase && hasUppercase && hasDigit && hasSpecial;
	}

	private static boolean isSpecialCharacter(char c) {
		String specialChars = "!@#$%^&*()_+{}:\"<>?|[];',./`~\\-=";
		return specialChars.indexOf(c) != -1;
	}
}
