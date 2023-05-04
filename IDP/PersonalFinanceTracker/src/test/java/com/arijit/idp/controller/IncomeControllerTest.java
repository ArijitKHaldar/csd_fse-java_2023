package com.arijit.idp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.arijit.idp.entity.Income;
import com.arijit.idp.service.IncomeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class IncomeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private IncomeServiceImpl incomeService;

	@InjectMocks
	private IncomeController incomeController;

	private ObjectMapper objectMapper;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(incomeController).build();
		objectMapper = new ObjectMapper();
	}

	@Test
	public void testInsertIncome() throws Exception {
		Income income = new Income();
		income.setUserId("1");
		income.setIncomeAmount(new BigDecimal(10000.0));
		income.setIncomeDate(Date.valueOf("2023-05-01"));

		when(incomeService.insertIncome(any(Income.class))).thenReturn(income);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/income/v1")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(income));

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
		assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(income));
	}

	@Test
	public void testFindByUserId() throws Exception {
		Income income1 = new Income();
		income1.setUserId("1");
		income1.setIncomeAmount(new BigDecimal(10000.0));
		income1.setIncomeDate(Date.valueOf("2023-05-01"));
		List<Income> incomeList = new ArrayList<>();
		incomeList.add(income1);

		when(incomeService.findByUserId(Mockito.anyString())).thenReturn(incomeList);

		mockMvc.perform(get("/api/income/v1/userid/{userId}", "1")).andExpect(status().isFound())
				.andExpect(jsonPath("$").isArray());

		verify(incomeService, times(1)).findByUserId(Mockito.anyString());
	}

	@Test
	public void testFindByUserIdAndMonth_Success() throws Exception {
		when(incomeService.findByUserIdAndMonth(Mockito.anyString(), Mockito.anyInt())).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/income/v1/userid/{userId}/month/{month}", "test-user", 1))
		.andExpect(status().isFound())
		.andExpect(jsonPath("$").isArray());

		verify(incomeService, times(1)).findByUserIdAndMonth(Mockito.anyString(), Mockito.anyInt());
	}

	@Test
	public void testFindByUserIdAndYear_Success() throws Exception {
		when(incomeService.findByUserIdAndYear(Mockito.anyString(), Mockito.anyInt())).thenReturn(Collections.emptyList());

		mockMvc.perform(get("/api/income/v1/userid/{userId}/year/{year}", "test-user", 2022))
		.andExpect(status().isFound())
		.andExpect(jsonPath("$").isArray());

		verify(incomeService, times(1)).findByUserIdAndYear(Mockito.anyString(), Mockito.anyInt());
	}

	@Test
	public void testUpdateByIncomeId() throws Exception {
		Income income1 = new Income();
		Field field = null;
		try {
			field = income1.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(income1, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		income1.setUserId("1");
		income1.setIncomeAmount(new BigDecimal(10000.0));
		income1.setIncomeDate(Date.valueOf("2023-05-01"));

		when(incomeService.updateByIncomeId(Mockito.eq(1), any(Income.class))).thenReturn(income1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/income/v1/update")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(income1))
				.param("incomeId", "1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(income1));
	}

	@Test
	public void testDeleteByIncomeId() throws Exception {
		int incomeId = 1;

		doNothing().when(incomeService).deleteByIncomeId(incomeId);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/income/v1/delete/id/" + incomeId);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}

}
