package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Login;
import com.arijit.idp.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	public void insert(Login login) {
		loginRepository.save(login);
	}
	
	public Login findLoginByUserId(String userId) {
		Login login = loginRepository.findLoginByUserId(userId);
		return login;
	}
	
	public Login findLoginByEmailId(String emailId) {
		Login login = loginRepository.findLoginByEmailId(emailId);
		return login;
	}
}
