package com.arijit.idp.service;

import java.sql.Date;
import java.util.List;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;

public interface ExpenditureService {

	// Create
	public Expenditure create(Expenditure expenditure)
			throws ExpenditureAlreadyPresentException, InvalidDataFormatException;

	// Retrieve
	public List<Expenditure> findByUserId(String userId)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException;

	public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException,
			InvalidDataFormatException;

	public List<Expenditure> findByUserIdAndMonth(String userId, int month) throws ExpenditureNotFoundException,
			NullValueEnteredException, NotAStringException, InvalidDataFormatException;

	public List<Expenditure> findByUserIdAndYear(String userId, int year) throws ExpenditureNotFoundException,
			NullValueEnteredException, NotAStringException, InvalidDataFormatException;

	public List<Expenditure> findByUserIdAndExpenditureType(String userId, String expenditureTag)
			throws ExpenditureNotFoundException, CategoryNotFoundException;

	// Update
	public Expenditure update(int expenditureId, Expenditure expenditure)
			throws ExpenditureNotFoundException, NullValueEnteredException, InvalidDataFormatException;

	// Delete
	public void delete(int expenditureId)
			throws ExpenditureNotFoundException, NullValueEnteredException, InvalidDataFormatException;

}
