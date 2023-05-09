package com.arijit.idp.controller;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.usecases.SavingsCalculation;

@WebMvcTest(SavingsCalculationController.class)
public class SavingsCalculationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private SavingsCalculation savingsCalculation;

	@Test
	void calculateMonthlySavingsPercentage_ValidData_ReturnsOk() throws Exception {
		double savingsPercentage = 25.0;

		when(savingsCalculation.calculateMonthlySavingsPercentage(anyString(), anyInt())).thenReturn(savingsPercentage);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/savings/calculation/v1/user/{userId}/savings/month/{month}",
				"user1", 5)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(String.valueOf(savingsPercentage)));
	}

	@Test
	void calculateMonthlySavingsPercentage_InvalidData_ReturnsBadRequest() throws Exception {
		String errorMessage = "Invalid data format";

		when(savingsCalculation.calculateMonthlySavingsPercentage(anyString(), anyInt()))
				.thenThrow(new InvalidDataFormatException(errorMessage));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/savings/calculation/v1/user/{userId}/savings/month/{month}",
				"user1", 5)).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
	}

	@Test
	void calculateMonthlySavingsPercentage_ExpenditureNotFound_ReturnsNotFound() throws Exception {
		String errorMessage = "Expenditure not found";

		when(savingsCalculation.calculateMonthlySavingsPercentage(anyString(), anyInt()))
				.thenThrow(new ExpenditureNotFoundException(errorMessage));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/savings/calculation/v1/user/{userId}/savings/month/{month}",
				"user1", 5)).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().string(errorMessage));
	}

	@Test
	void calculateYearlySavingsPercentage_Valid() throws Exception {

		double savingsPercentage = 30;
		when(savingsCalculation.calculateYearlySavingsPercentage(anyString(), anyInt())).thenReturn(savingsPercentage);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/savings/calculation/v1/user/{userId}/savings/year/{year}",
				"user1", 2023)).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(String.valueOf(savingsPercentage)));
	}
}
