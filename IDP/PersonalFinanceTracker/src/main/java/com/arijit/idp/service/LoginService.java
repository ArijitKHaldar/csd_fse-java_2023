package com.arijit.idp.service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;

public interface LoginService {

	public Login signup(Login loginWithoutUserId) throws LoginAlreadyPresentException;

	public Login findLoginByUserId(String userId) throws LoginNotFoundException;

	public Login findLoginByEmailId(String emailId) throws LoginNotFoundException;
}
