package dao;

import java.util.List;

import model.Income;

public interface IncomeDAO {
	// Create
	public void insert(Income income);

	// Retrieve
	public Income getByUserId(int user_id);

	// Update
	public void update(Income income);

	// Delete
	public void deleteByIncomeId(int id);

	// Get All
	public List<Income> getAll();
}
