package com.arijit.idp.service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.exception.NullValueEnteredException;

public interface LoginService {

	public Login signup(Login loginWithoutUserId)
			throws LoginAlreadyPresentException, InvalidDataFormatException, NullValueEnteredException;

	public Login findLoginByUserId(String userId) throws LoginNotFoundException, NullValueEnteredException;

	public Login findLoginByEmailId(String emailId) throws LoginNotFoundException, NullValueEnteredException;
}
