package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.helpermodules.InputValidator;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private UserIdGenerator uig;

	public Login signup(Login loginWithoutUserId) throws LoginAlreadyPresentException, NotAStringException,
			InvalidDataFormatException, NullValueEnteredException {
		Login login = loginWithoutUserId;
		if (loginWithoutUserId.getEmailId() == null || loginWithoutUserId.getEmailId().isEmpty()) {
			throw new NullValueEnteredException("Email id cannot be empty");
		}
		if (!(loginWithoutUserId.getEmailId() instanceof String)) {
			throw new NotAStringException("The entered email id is not a String value");
		}
		if (!InputValidator.isValidEmail(loginWithoutUserId.getEmailId())) {
			throw new InvalidDataFormatException();
		}
		if (loginWithoutUserId.getPassword() == null || loginWithoutUserId.getPassword().isEmpty()) {
			throw new NullValueEnteredException("Password cannot be empty");
		}
		if (!(loginWithoutUserId.getPassword() instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}
		if (!InputValidator.isValidPassword(loginWithoutUserId.getPassword())) {
			throw new InvalidDataFormatException();
		}
		login.setUserId(uig.generateUserId(loginWithoutUserId.getEmailId()));

		if (loginRepository.existsById(login.getUserId())) {
			throw new LoginAlreadyPresentException();
		}

		return loginRepository.save(login);
	}

	public Login findLoginByUserId(String userId)
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		if (!(userId instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}
		Login login = loginRepository.findById(userId).orElse(null);
		if (login == null) {
			throw new LoginNotFoundException();
		}

		return login;
	}

	public Login findLoginByEmailId(String emailId)
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {

		if (emailId == null || emailId.isEmpty()) {
			throw new NullValueEnteredException("Email Id cannot be empty");
		}
		if (!(emailId instanceof String)) {
			throw new NotAStringException("The entered email id is not a String value");
		}
		Login login = loginRepository.findByEmailId(emailId);
		if (login == null) {
			throw new LoginNotFoundException();
		}

		return login;
	}
}
