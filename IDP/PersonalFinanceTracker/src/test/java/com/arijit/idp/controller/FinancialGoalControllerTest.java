package com.arijit.idp.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.arijit.idp.usecases.FinancialGoal;

@WebMvcTest(FinancialGoalController.class)
public class FinancialGoalControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FinancialGoal financialGoal;

	@Test
	public void testCalculateSavingsCompletion() throws Exception {
		double expectedPercentageCompletion = 50.0;
		when(financialGoal.calculateSavingsCompletion(anyString(), any(Date.class), anyDouble()))
				.thenReturn(expectedPercentageCompletion);

		mockMvc.perform(
				get("/api/v1/goal/user/testuser/date/2023-01-01/amount/10000").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().string(Double.toString(expectedPercentageCompletion)));
	}
}
