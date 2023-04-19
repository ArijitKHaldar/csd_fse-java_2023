package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Login;
import com.arijit.idp.repository.LoginRepository;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class LoginServiceImplTest {

	@InjectMocks
	private LoginServiceImpl loginServiceImpl;

	@Mock
	private LoginRepository loginRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@Disabled
	public void testSignup() {
		// Mock the Login object
		Login mockLogin = new Login("testuser", "testpass", "test@example.com");

		// Call the LoginService method
		loginServiceImpl.signup(mockLogin);

		// Verify that the repository method was called with the correct argument
		verify(loginRepository, times(1)).save(mockLogin);
	}

	@Test
	public void testFindLoginByUserId() {
		// Given
		String userId = "testuser";
		Login expectedLogin = new Login();
		expectedLogin.setUserId(userId);
		when(loginRepository.findById(userId)).thenReturn(Optional.of(expectedLogin));

		// When
		Login actualLogin = loginServiceImpl.findLoginByUserId(userId);

		// Then
		assertEquals(expectedLogin, actualLogin);
	}

	@Test
	public void testFindLoginByEmailId() {
		// Given
		String emailId = "test@example.com";
		Login expectedLogin = new Login();
		expectedLogin.setEmailId(emailId);
		when(loginRepository.findByEmailId(emailId)).thenReturn(expectedLogin);

		// When
		Login actualLogin = loginServiceImpl.findLoginByEmailId(emailId);

		// Then
		assertEquals(expectedLogin, actualLogin);
	}
}