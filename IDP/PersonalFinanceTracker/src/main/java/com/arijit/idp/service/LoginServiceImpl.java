package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.repository.LoginRepository;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserIdGenerator uig;

	public void signup(Login loginWithoutUserId) {
		Login login = loginWithoutUserId;
		login.setUserId(uig.generateUserId(loginWithoutUserId.getEmailId()));
		loginRepository.save(login);
	}

	public Login findLoginByUserId(String userId) {
		Login login = loginRepository.findById(userId).orElse(null);
		return login;
	}

	public Login findLoginByEmailId(String emailId) {
		Login login = loginRepository.findByEmailId(emailId);
		return login;
	}
}
