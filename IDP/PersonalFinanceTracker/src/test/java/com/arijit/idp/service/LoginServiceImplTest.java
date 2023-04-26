package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Login;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.repository.LoginRepository;

@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {

	@InjectMocks
	private LoginServiceImpl loginServiceImpl;

	@Mock
	private UserIdGenerator mockUserIdGenerator;

	@Mock
	private LoginRepository mockLoginRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSignup() throws LoginAlreadyPresentException, LoginNotFoundException {
		// Mock the Login object
		Login mockLogin = new Login();
		mockLogin.setEmailId("test@example.com");
		mockLogin.setPassword("testpass");

		// Mock the UserIdGenerator
		when(mockUserIdGenerator.generateUserId(mockLogin.getEmailId())).thenReturn("testuser");

		// Call the LoginService method
		loginServiceImpl.signup(mockLogin);
		assertEquals(mockLogin.getUserId(), "testuser");
		
		// Verify that the repository method was called with the correct argument
		verify(mockLoginRepository).save(mockLogin);

		// Find login data with the test user id
		when(mockLoginRepository.findById("testuser")).thenReturn(Optional.of(mockLogin));
		Login testLogin = loginServiceImpl.findLoginByUserId("testuser");

		// Test if the Login worked with the data provided
		assertEquals(mockLogin, testLogin);
	}

	@Test
	public void testFindLoginByUserId() throws LoginNotFoundException {

		Login expectedLogin = new Login("test@example.com", "testpass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findById("testuser")).thenReturn(Optional.of(expectedLogin));
		Login actualLogin = loginServiceImpl.findLoginByUserId("testuser");
		assertEquals(expectedLogin, actualLogin);
	}

	@Test
	public void testFindLoginByEmailId() throws LoginNotFoundException {

		Login expectedLogin = new Login("test@example.com", "testpass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findByEmailId("test@example.com")).thenReturn(expectedLogin);
		Login actualLogin = loginServiceImpl.findLoginByEmailId("test@example.com");
		assertEquals(expectedLogin, actualLogin);
	}
}