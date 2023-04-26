package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserIdGenerator uig;

	public Login signup(Login loginWithoutUserId) throws LoginAlreadyPresentException {
		Login login = loginWithoutUserId;
		login.setUserId(uig.generateUserId(loginWithoutUserId.getEmailId()));
		
		if(loginRepository.existsById(login.getUserId())) {
			throw new LoginAlreadyPresentException();
		}
		
		return loginRepository.save(login);
	}

	public Login findLoginByUserId(String userId) throws LoginNotFoundException {
		Login login = loginRepository.findById(userId).orElse(null);
		if(login == null) {
			throw new LoginNotFoundException();
		}
		
		return login;
	}

	public Login findLoginByEmailId(String emailId) throws LoginNotFoundException {
		Login login = loginRepository.findByEmailId(emailId);
		if(login == null) {
			throw new LoginNotFoundException();
		}
		
		return login;
	}
}
