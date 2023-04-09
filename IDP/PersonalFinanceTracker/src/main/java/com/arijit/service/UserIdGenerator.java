package com.arijit.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
public class UserIdGenerator {
	private String userId;

	public UserIdGenerator() {
		super();
	}

	public UserIdGenerator(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	public String generateUserId(String emailId) {
		
		//Remove the part of the email id from @<domainname>.<topleveldomain>
		int index = emailId.indexOf('@');
		emailId = emailId.substring(0, index);
		//Keep only alphanumeric characters
		String regex = "[^a-zA-Z0-9]";
		emailId = emailId.replaceAll(regex, "");
		
        // Get the current timestamp in milliseconds
		LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        String timestamp = dateTime.format(formatter);
        
        StringBuilder userIdBuilder = new StringBuilder();
        
        // Take the first 5 characters of emailId, or pad with zeroes if less than 5 characters
        String emailPrefix = emailId.substring(0, Math.min(emailId.length(), 5));
        emailPrefix = String.format("%-5s", emailPrefix).replace(' ', '0');
        
        // Split timestamp into individual digits
        String[] timestampDigits = timestamp.split("(?<=.)");
        
        int emailIndex = 0;
        int timestampIndex = 0;
        
        // Alternate between adding a character from emailPrefix and a digit from timestampDigits
        while (emailIndex < emailPrefix.length() && timestampIndex < timestampDigits.length) {
            userIdBuilder.append(emailPrefix.charAt(emailIndex++));
            userIdBuilder.append(timestampDigits[timestampIndex++]);
        }
        
        // Add remaining characters from emailPrefix and timestampDigits, if any
        if (emailIndex < emailPrefix.length()) {
            userIdBuilder.append(emailPrefix.substring(emailIndex));
        }
        
        if (timestampIndex < timestampDigits.length) {
            userIdBuilder.append(String.join("", Arrays.copyOfRange(timestampDigits, timestampIndex, timestampDigits.length)));
        }
        
        return userIdBuilder.toString();
	}
	
}
