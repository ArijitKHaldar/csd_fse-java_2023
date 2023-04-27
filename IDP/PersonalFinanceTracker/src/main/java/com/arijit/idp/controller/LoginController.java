package com.arijit.idp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.LoginService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private LoginService service;

	@ApiOperation(value = "Signup with user id and password")
	@PostMapping("/v1")
	public ResponseEntity<Login> signup(@RequestBody Login login) throws LoginAlreadyPresentException, NotAStringException,
			InvalidDataFormatException, NullValueEnteredException {
		return new ResponseEntity<>(service.signup(login), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Retrieve data based on entered user id")
	@GetMapping("/v1/userid/{userId}")
	public ResponseEntity<Login> findLoginByUserId(@PathVariable String userId)
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {
		return new ResponseEntity<>(service.findLoginByUserId(userId), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Retrieve data based on entered user id")
	@GetMapping("/v1/email/{emailId}")
	public ResponseEntity<Login> findLoginByEmailId(@PathVariable String emailId)
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {
		return new ResponseEntity<>(service.findLoginByEmailId(emailId), HttpStatus.FOUND);
	}
}