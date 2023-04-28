package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

	@InjectMocks
	private CategoryServiceImpl mockedService;

	@Mock
	private CategoryRepository mockRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateCategory_Succeed() throws CategoryAlreadyPresentException {

		Category mockCategory = new Category(1, "testcategory");
		mockCategory.setExpenditureTag(mockCategory.getExpenditureTag().toUpperCase());

		when(mockRepository.save(mockCategory)).thenReturn(mockCategory);
		Category testCategory = new Category();
		testCategory = mockedService.create(mockCategory);
		assertEquals(mockCategory, testCategory);
		verify(mockRepository, times(1)).save(mockCategory);
	}

	@Test
	public void testCreateCategory_AlreadyPresent() {

		Category mockCategory = new Category("food");
		List<Category> categories = new ArrayList<>();
		categories.add(new Category("FOOD"));
		when(mockRepository.findAll()).thenReturn(categories);
		assertThrows(CategoryAlreadyPresentException.class, () -> mockedService.create(mockCategory));
		verify(mockRepository, never()).save(any(Category.class));
	}

	@Test
	void testFindAllExpenditureTags() throws CategoryNotFoundException {
		List<Category> expectedCategories = new ArrayList<>();
		expectedCategories.add(new Category("category1"));
		expectedCategories.add(new Category("category2"));
		when(mockRepository.findAll()).thenReturn(expectedCategories);

		List<Category> actualCategories = mockedService.findAllExpenditureTags();

		assertEquals(expectedCategories, actualCategories);
		assertTrue(actualCategories.containsAll(expectedCategories));
	}

	@Test
	void testFindAllExpenditureTags_CategoryNotFound() {
	    when(mockRepository.findAll()).thenReturn(Collections.emptyList());
	    assertThrows(CategoryNotFoundException.class, () -> mockedService.findAllExpenditureTags());
	}

	@Test
	void testDeleteByExpenditureTag_Success() throws CategoryNotFoundException {
		String expenditureTag = "Food";
		Category category = new Category(expenditureTag);
		when(mockRepository.findByExpenditureTagIgnoreCase(expenditureTag)).thenReturn(category);

		mockedService.deleteByExpenditureTag(expenditureTag);

		verify(mockRepository, times(1)).delete(category);
	}

	@Test
	void testDeleteByExpenditureTag_CategoryNotFound() {
		String expenditureTag = "Food";
		when(mockRepository.findByExpenditureTagIgnoreCase(expenditureTag)).thenReturn(null);

		assertThrows(CategoryNotFoundException.class, () -> {
			mockedService.deleteByExpenditureTag(expenditureTag);
		});
		verify(mockRepository, never()).delete(any(Category.class));
	}

}
