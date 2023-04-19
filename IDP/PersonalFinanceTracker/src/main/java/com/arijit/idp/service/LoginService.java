package com.arijit.idp.service;

import com.arijit.idp.entity.Login;

public interface LoginService {

	public void signup(Login loginWithoutUserId);

	public Login findLoginByUserId(String userId);

	public Login findLoginByEmailId(String emailId);
}
