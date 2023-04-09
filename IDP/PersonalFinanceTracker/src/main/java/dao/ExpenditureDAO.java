package dao;

import java.util.List;

import model.Expenditure;

public interface ExpenditureDAO {
	// Create
	public void insert(Expenditure expenditure);

	// Retrieve
	public Expenditure getByUserId(String user_id);

	// Update
	public void update(Expenditure expenditure);

	// Delete
	public void deleteByExpenditureId(int expenditure_id);

	// Get All
	public List<Expenditure> getAll();
}
