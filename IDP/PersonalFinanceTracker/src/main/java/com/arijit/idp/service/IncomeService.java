package com.arijit.idp.service;

import java.util.List;

import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.IncomeAlreadyPresentException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;

public interface IncomeService {

	// Create
	public Income insertIncome(Income income) throws IncomeAlreadyPresentException, InvalidDataFormatException;

	// Retrieve
	public List<Income> findByUserId(String userId)
			throws IncomeNotFoundException, NullValueEnteredException, NotAStringException;

	public List<Income> findByUserIdAndMonth(String userId, int month)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException;

	public List<Income> findByUserIdAndYear(String userId, int year)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException;

	// Update
	public Income updateByIncomeId(int incomeId, Income updatedIncome)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException;

	// Delete
	public void deleteByIncomeId(int incomeId)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException;
}
