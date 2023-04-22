package com.arijit.idp.service;

import java.util.List;

import com.arijit.idp.entity.Income;

public interface IncomeService {
	
	// Create
	public String insertIncome(Income income);
	
	// Retrieve
	public List<Income> findByUserId(String userId);
	
	public List<Income> findByUserIdAndMonth(String userId, int month);
	
	public List<Income> findByUserIdAndYear(String userId, int year);
	
	// Update
	public String updateByIncomeId(int incomeId, Income updatedIncome);
	
	// Delete
	public String deleteByIncomeId(int incomeId);
}
