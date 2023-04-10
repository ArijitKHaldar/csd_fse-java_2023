package com.arijit.dao;

import com.arijit.model.Category;

public interface CategoryDAO {
	// Create
	public void insert(Category category);

	// Retrieve
	public Category getByExpenditureId(int expenditureId);

	// Update
	public void updateByExpenditureId(Category category, int expenditureId);
	
	// Delete
	public void deleteByExpenditureId(int expenditure_id);

}
