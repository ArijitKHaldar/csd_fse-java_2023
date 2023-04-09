package com.arijit.dao;

import java.util.List;

import com.arijit.model.Income;

public interface IncomeDAO {
	// Create
	public void insert(Income income);

	// Retrieve
	public List<Income> getByUserId(String user_id);
	public List<Income> getByUserIdAndMonth(String user_id, int month);
	public List<Income> getByUserIdAndYear(String user_id, int year);

	// Update
	public void updateByIncomeId(Income income, int income_id);

	// Delete
	public void deleteByIncomeId(int income_id);
}
