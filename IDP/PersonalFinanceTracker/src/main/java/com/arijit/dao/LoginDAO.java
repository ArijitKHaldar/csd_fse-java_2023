package com.arijit.dao;

import com.arijit.model.Login;

public interface LoginDAO {
	//Create
	public void insert(Login user, String emailId);
	//Retrieve
	public Login getByEmailId(String emailId);
	public Login getByUserId(String userId);
}
