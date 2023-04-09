package com.arijit.model;

public class Login {
	private String userId;
	private String emailId;
	private String password;

	public Login() {
		super();
	}

	public Login(String userId, String emailId, String password) {
		super();
		this.userId = userId;
		this.emailId = emailId;
		this.password = password;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Login [userId=" + userId + ", emailId=" + emailId + ", password=" + password + "]";
	}
}
