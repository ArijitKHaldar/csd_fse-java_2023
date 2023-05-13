package com.arijit.idp.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.arijit.idp.usecases.BudgetForecasting;

@WebMvcTest(BudgetForecastingController.class)
public class BudgetForecastingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BudgetForecasting budgetForecasting;

	@Test
	void testPredictSavings() throws Exception {
		String userId = "testUser";
		Date currentDate = new Date(System.currentTimeMillis());

		when(budgetForecasting.predictSavings(Mockito.anyString())).thenReturn(5000.0);

		mockMvc.perform(
				get("/api/v1/predict/user/{userId}", userId, currentDate).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().json("5000.0"));
	}

}
