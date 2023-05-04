package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
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
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.LoginAlreadyPresentException;
import com.arijit.idp.exception.LoginNotFoundException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
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
	public void testSignup_signupSuccessfull() throws LoginAlreadyPresentException, LoginNotFoundException,
			NotAStringException, InvalidDataFormatException, NullValueEnteredException {
		Login mockLogin = new Login();
		mockLogin.setEmailId("test@example.com");
		mockLogin.setPassword("tE$t1pass");

		// Mock the UserIdGenerator
		when(mockUserIdGenerator.generateUserId(mockLogin.getEmailId())).thenReturn("testuser");

		loginServiceImpl.signup(mockLogin);
		assertEquals("testuser", mockLogin.getUserId());

		verify(mockLoginRepository).save(mockLogin);

		when(mockLoginRepository.findById("testuser")).thenReturn(Optional.of(mockLogin));
		Login testLogin = loginServiceImpl.findLoginByUserId("testuser");

		assertEquals(mockLogin, testLogin);
	}

	@Test
	public void testSignup_signupFailed() {

		Login mockLogin = new Login();
		mockLogin.setUserId("testuser");
		mockLogin.setEmailId("test@example.com");
		mockLogin.setPassword("tE$st1pass");

		// Mock the UserIdGenerator
		when(mockUserIdGenerator.generateUserId(mockLogin.getEmailId())).thenReturn("testuser");

		when(mockLoginRepository.existsById("testuser")).thenReturn(true);

		assertThrows(LoginAlreadyPresentException.class, () -> loginServiceImpl.signup(mockLogin));
		verify(mockLoginRepository, never()).save(any(Login.class));

	}

	@Test
	public void testFindLoginByUserId_UserIdFound()
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {

		Login expectedLogin = new Login("test@example.com", "tE$t1pass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findById("testuser")).thenReturn(Optional.of(expectedLogin));
		Login actualLogin = loginServiceImpl.findLoginByUserId("testuser");
		assertEquals(expectedLogin, actualLogin);
	}

	@Test
	public void testFindLoginByUserId_UserIdNotFound() {
		Login expectedLogin = new Login("test@example.com", "tE$t1pass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findById("newuser")).thenReturn(Optional.empty());
		assertThrows(LoginNotFoundException.class, () -> loginServiceImpl.findLoginByUserId("newuser"));
	}

	@Test
	public void testFindLoginByEmailId_EmailIdFound()
			throws LoginNotFoundException, NullValueEnteredException, NotAStringException {

		Login expectedLogin = new Login("test@example.com", "tE$t1pass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findByEmailId("test@example.com")).thenReturn(expectedLogin);
		Login actualLogin = loginServiceImpl.findLoginByEmailId("test@example.com");
		assertEquals(expectedLogin, actualLogin);
	}

	@Test
	public void testFindLoginByEmailId_EmailIdNotFound() {

		Login expectedLogin = new Login("test@example.com", "tE$t1pass");
		expectedLogin.setUserId("testuser");
		when(mockLoginRepository.findByEmailId("new@example.com")).thenReturn(null);
		assertThrows(LoginNotFoundException.class, () -> loginServiceImpl.findLoginByEmailId("new@example.com"));
	}

	@Test
	public void testSignup_NullValueEnteredException() {
		Login login = new Login(null, "test123");
		assertThrows(NullValueEnteredException.class, () -> loginServiceImpl.signup(login));

		Login login1 = new Login("test123@mail.com", null);
		assertThrows(NullValueEnteredException.class, () -> loginServiceImpl.signup(login1));

		Login login2 = new Login("test123", "testpass");
		assertThrows(InvalidDataFormatException.class, () -> loginServiceImpl.signup(login2));

		Login login3 = new Login("test123@mail.com", "testpass");
		assertThrows(InvalidDataFormatException.class, () -> loginServiceImpl.signup(login3));
	}

	@Test
	public void testFindByUserId_NullValue() {
		assertThrows(NullValueEnteredException.class, () -> loginServiceImpl.findLoginByUserId(null));
	}

	@Test
	public void testFindByEmail_NullValue() {
		assertThrows(NullValueEnteredException.class, () -> loginServiceImpl.findLoginByEmailId(null));
	}
}