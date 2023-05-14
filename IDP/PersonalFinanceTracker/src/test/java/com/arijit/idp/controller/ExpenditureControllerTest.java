package com.arijit.idp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.service.ExpenditureServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ExpenditureController.class)
public class ExpenditureControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ExpenditureServiceImpl expenditureService;

	@Autowired
	private ObjectMapper objectMapper;

	private List<Expenditure> expenditureList;

	@Test
	void testCreateExpenditure() throws Exception {
		Expenditure expenditure = new Expenditure();
		expenditure.setUserId("user1");
		expenditure.setCategoryId(1);
		expenditure.setExpenditureDate(new Date(System.currentTimeMillis()));
		expenditure.setExpenditureAmount(BigDecimal.valueOf(100.0));

		when(expenditureService.create(expenditure)).thenReturn(expenditure);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/expenditure/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(expenditure))).andExpect(status().isCreated());
	}

	@Test
	void testFindByUserId() throws Exception {
		Expenditure expenditure1 = new Expenditure();
		expenditure1.setUserId("user1");
		expenditure1.setCategoryId(1);
		expenditure1.setExpenditureDate(new Date(System.currentTimeMillis()));
		expenditure1.setExpenditureAmount(BigDecimal.valueOf(100.0));

		Expenditure expenditure2 = new Expenditure();
		expenditure2.setUserId("user1");
		expenditure2.setCategoryId(2);
		expenditure2.setExpenditureDate(new Date(System.currentTimeMillis()));
		expenditure2.setExpenditureAmount(BigDecimal.valueOf(200.0));

		List<Expenditure> expenditureList = Arrays.asList(expenditure1, expenditure2);

		when(expenditureService.findByUserId("user1")).thenReturn(expenditureList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/expenditure/v1/userid/user1")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].userId", is("user1"))).andExpect(jsonPath("$[0].categoryId", is(1)))
				.andExpect(jsonPath("$[0].expenditureAmount", is(100.0)))
				.andExpect(jsonPath("$[1].userId", is("user1"))).andExpect(jsonPath("$[1].categoryId", is(2)))
				.andExpect(jsonPath("$[1].expenditureAmount", is(200.0)));
	}

	@Test
	void findByUserIdAndExpenditureDateTest() throws Exception {
		String userId = "user1";
		Date expenditureDate = new Date(System.currentTimeMillis());
		Expenditure expenditure1 = new Expenditure(userId, 1, expenditureDate, new BigDecimal("100.00"));
		Expenditure expenditure2 = new Expenditure(userId, 2, expenditureDate, new BigDecimal("200.00"));
		List<Expenditure> expenditureList = new ArrayList<>();
		expenditureList.add(expenditure1);
		expenditureList.add(expenditure2);
		when(expenditureService.findByUserIdAndExpenditureDate(userId, expenditureDate)).thenReturn(expenditureList);

		mockMvc.perform(get("/api/expenditure/v1/userid/{userId}/date/{expenditureDate}", userId, expenditureDate))
				.andExpect(status().isOk());
	}

	@Test
	void testFindByUserIdAndMonth() throws Exception {
		when(expenditureService.findByUserIdAndMonth("userId1", 1)).thenReturn(expenditureList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/expenditure/v1/userid/userId1/month/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	void testFindByUserIdAndYear() throws Exception {
		when(expenditureService.findByUserIdAndYear("userId1", 2022)).thenReturn(expenditureList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/expenditure/v1/userid/userId1/year/2022")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	void testFindByUserIdAndExpenditureType() throws Exception {
		when(expenditureService.findByUserIdAndExpenditureType("userId1", "tag1")).thenReturn(expenditureList);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/expenditure/v1/userid/userId1/category/tag1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	void testUpdateByIncomeId() throws Exception {
		Expenditure expenditure = new Expenditure();
		Field field = null;
		try {
			field = expenditure.getClass().getDeclaredField("expenditureId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(expenditure, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		expenditure.setUserId("1");
		expenditure.setExpenditureAmount(new BigDecimal(10000.0));
		expenditure.setExpenditureDate(Date.valueOf("2023-05-01"));

		when(expenditureService.update(Mockito.eq(1), any(Expenditure.class))).thenReturn(expenditure);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/expenditure/v1/update")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(expenditure))
				.param("expenditureId", "1");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(expenditure));
	}

	@Test
	void testDeleteByExpenditureId() throws Exception {
		int expenditureId = 1;

		doNothing().when(expenditureService).delete(expenditureId);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/expenditure/v1/delete/id/" + expenditureId);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
	}
}
