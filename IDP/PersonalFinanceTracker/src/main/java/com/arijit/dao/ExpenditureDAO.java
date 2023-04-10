package com.arijit.dao;

import java.util.List;

import com.arijit.model.Expenditure;

public interface ExpenditureDAO {
	// Create
	public void insert(Expenditure expenditure);

	// Retrieve
	public int getLastExpenditureId();
	public List<Expenditure> getByUserId(String user_id);
	public List<Expenditure> getByUserIdAndMonth(String user_id, int month);
	public List<Expenditure> getByUserIdAndYear(String user_id, int year);

	// Update
	public void updateByExpenditureId(Expenditure expenditure, int expenditure_id);

	// Delete
	public void deleteByExpenditureId(int expenditure_id);
}
