package com.arijit.idp.service;

import java.util.List;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.exception.CategoryNotFoundException;

public interface CategoryService {

	// Create
	public Category create(Category category) throws CategoryAlreadyPresentException;

	// Retrieve
	public List<Category> findAllExpenditureTags() throws CategoryNotFoundException;

	// Delete
	public void deleteByExpenditureTag(String expenditureTag) throws CategoryNotFoundException;
}
