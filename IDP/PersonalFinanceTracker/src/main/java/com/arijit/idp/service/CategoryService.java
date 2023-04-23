package com.arijit.idp.service;

import java.util.List;

import com.arijit.idp.entity.Category;

public interface CategoryService {

	// Create
	public Category create(Category category);

	// Retrieve
	public List<Category> findAllExpenditureTags();

	// Delete
	public void deleteByExpenditureTag(String expenditureTag);
}
