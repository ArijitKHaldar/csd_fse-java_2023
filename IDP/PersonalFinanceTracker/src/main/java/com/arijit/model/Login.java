package com.arijit.model;

public class Login {
	private String email_id;
	private String password;

	/**
	 * @param email_id
	 * @param password
	 */
	public Login(String email_id, String password) {
		super();
		this.email_id = email_id;
		this.password = password;
	}

	/**
	 * 
	 */
	public Login() {
		super();
	}

	/**
	 * @return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/**
	 * @param email_id the email_id to set
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Income [email_id=" + email_id + ", password=" + password + "]";
	}

}
