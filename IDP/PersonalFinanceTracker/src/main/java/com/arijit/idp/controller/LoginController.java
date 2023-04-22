package com.arijit.idp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Login;
import com.arijit.idp.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private LoginService service;

	@ApiOperation(value = "Signup with user id and password")
	@PostMapping("/v1")
	public void signup(@RequestBody Login login) {
		service.signup(login);
	}

	@ApiOperation(value = "Retrieve data based on entered user id")
	@GetMapping("/v1/userid/{userId}")
	public Login findLoginByUserId(@PathVariable String userId) {
		return service.findLoginByUserId(userId);
	}

	@ApiOperation(value = "Retrieve data based on entered user id")
	@GetMapping("/v1/email/{emailId}")
	public Login findLoginByEmailId(@PathVariable String emailId) {
		return service.findLoginByEmailId(emailId);
	}
}