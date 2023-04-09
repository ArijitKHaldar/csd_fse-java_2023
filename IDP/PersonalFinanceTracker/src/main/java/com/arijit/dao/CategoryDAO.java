package com.arijit.dao;

import java.util.List;

import com.arijit.model.Category;

public interface CategoryDAO {
	// Create
	public void insert(Category category);

	// Retrieve
	public List<Category> getByExpenditureId(int expenditureId);

	// Update
	public void updateByExpenditureId(Category category, int expenditureId);
	
	// Delete
	public void deleteByExpenditureId(int expenditure_id);

}
