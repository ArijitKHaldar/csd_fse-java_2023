package com.arijit.idp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.service.CategoryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc
public class CategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CategoryServiceImpl categoryService;

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

	@Test
	public void testFindAllExpenditureTags() throws Exception {
		Category category1 = new Category();
		category1.setExpenditureTag("Food");
		Field field = null;
		try {
			field = category1.getClass().getDeclaredField("categoryId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(category1, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		Category category2 = new Category();
		category2.setExpenditureTag("Transportation");
		field = null;
		try {
			field = category2.getClass().getDeclaredField("categoryId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(category2, 2);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		List<Category> categories = new ArrayList<>();
		categories.add(category1);
		categories.add(category2);

		when(categoryService.findAllExpenditureTags()).thenReturn(categories);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/v1/view")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(result.getResponse().getContentAsString()).isEqualTo(objectMapper.writeValueAsString(categories));
	}

	@Test
	public void testDeleteByExpenditureTag() throws Exception {
		doNothing().when(categoryService).deleteByExpenditureTag("test");

		mockMvc.perform(delete("/api/category/v1/delete/{expenditureTag}", "test")).andExpect(status().isNoContent())
				.andExpect(content().string(""));

	}

}