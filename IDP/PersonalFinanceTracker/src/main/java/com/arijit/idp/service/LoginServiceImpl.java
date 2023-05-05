package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.helpermodules.InputValidator;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.repository.LoginRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private UserIdGenerator uig;

	@Override
	public Login signup(Login loginWithoutUserId)
			throws LoginAlreadyPresentException, InvalidDataFormatException, NullValueEnteredException {

		log.info("Entering signup method: {}", loginWithoutUserId);

		Login login = loginWithoutUserId;
		if (loginWithoutUserId.getEmailId() == null || loginWithoutUserId.getEmailId().isEmpty()) {
			throw new NullValueEnteredException("Email id cannot be empty");
		}
		if (!InputValidator.isValidEmail(loginWithoutUserId.getEmailId())) {
			throw new InvalidDataFormatException();
		}
		if (loginWithoutUserId.getPassword() == null || loginWithoutUserId.getPassword().isEmpty()) {
			throw new NullValueEnteredException("Password cannot be empty");
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

	@Override
	public Login findLoginByUserId(String userId) throws LoginNotFoundException, NullValueEnteredException {

		log.info("Entering findLoginByUserId method with userId: {}", userId);

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		Login login = loginRepository.findById(userId).orElse(null);
		if (login == null) {
			throw new LoginNotFoundException();
		}

		return login;
	}

	@Override
	public Login findLoginByEmailId(String emailId) throws LoginNotFoundException, NullValueEnteredException {

		log.info("Entering findLoginByEmailId method with emailId: {}", emailId);

		if (emailId == null || emailId.isEmpty()) {
			throw new NullValueEnteredException("Email Id cannot be empty");
		}
		Login login = loginRepository.findByEmailId(emailId);
		if (login == null) {
			throw new LoginNotFoundException();
		}

		return login;
	}
}
