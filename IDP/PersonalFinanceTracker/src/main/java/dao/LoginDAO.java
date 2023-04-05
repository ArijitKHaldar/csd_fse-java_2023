package dao;

import model.Login;

public interface LoginDAO {
	//Create
	public void insert(Login user);
	//Retrieve
	public Login getByEmailId(String email_id);
}
