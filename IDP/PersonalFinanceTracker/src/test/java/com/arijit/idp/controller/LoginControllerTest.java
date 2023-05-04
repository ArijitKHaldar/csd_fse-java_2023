package com.arijit.idp.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.arijit.idp.entity.Login;
import com.arijit.idp.service.LoginServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LoginServiceImpl loginService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void signup_withValidInput_shouldReturnCreated() throws Exception {
		Login login = new Login();
		login.setUserId("user1");
		login.setPassword("password1");

		when(loginService.signup(Mockito.any(Login.class))).thenReturn(login);

		mockMvc.perform(post("/api/login/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(login))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.userId", is("user1"))).andExpect(jsonPath("$.password", is("password1")));
	}

	@Test
	public void findLoginByUserId_withValidInput_shouldReturnFound() throws Exception {
		Login login = new Login();
		login.setUserId("user1");
		login.setPassword("password1");

		when(loginService.findLoginByUserId(anyString())).thenReturn(login);

		mockMvc.perform(get("/api/login/v1/userid/user1")).andExpect(status().isFound())
				.andExpect(jsonPath("$.userId", is("user1"))).andExpect(jsonPath("$.password", is("password1")));
	}

	@Test
	public void findLoginByEmailId_withValidInput_shouldReturnFound() throws Exception {
		Login login = new Login();
		login.setUserId("user1");
		login.setPassword("password1");
		login.setEmailId("user1@example.com");

		when(loginService.findLoginByEmailId(anyString())).thenReturn(login);

		mockMvc.perform(get("/api/login/v1/email/user1@example.com")).andExpect(status().isFound())
				.andExpect(jsonPath("$.userId", is("user1"))).andExpect(jsonPath("$.password", is("password1")))
				.andExpect(jsonPath("$.emailId", is("user1@example.com")));
	}
}
