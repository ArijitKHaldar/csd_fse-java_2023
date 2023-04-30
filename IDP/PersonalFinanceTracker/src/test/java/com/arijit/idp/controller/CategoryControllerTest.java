package com.arijit.idp.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CategoryService categoryService;

	@Test
	public void createCategoryTest() throws Exception {
		Category category = new Category();
		category.setExpenditureTag("Food");

		doReturn(category).when(categoryService).create(category);

		mockMvc.perform(post("/api/category/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category))).andExpect(status().isCreated());
	}

	@Test
	public void create_WhenCategoryAlreadyPresent() throws Exception {

		Category category = new Category();
		category.setExpenditureTag("Grocery");
		doThrow(new CategoryAlreadyPresentException("Category with name Grocery already exists")).when(categoryService)
				.create(category);

		MockHttpServletRequestBuilder request = post("/api/category/v1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(category));

		mockMvc.perform(request);
	}
}