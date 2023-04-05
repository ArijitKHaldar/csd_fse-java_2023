package dao;

import model.Category;

public interface CategoryDAO {
	// Create
	public void insert(Category category);

	// Retrieve
	public Category getByExpenditureId(int expenditure_id);

	// Update
	public void updateByCategoryId(Category category_id);

}
