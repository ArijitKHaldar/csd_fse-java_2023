package com.arijit.idp.service;

import java.sql.Date;
import java.util.List;

import com.arijit.idp.entity.Category;
import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureNotFoundException;

public interface ExpenditureService {

	
	//Create
	public Expenditure create(Expenditure expenditure, Category category);
	
//	//Retrieve
//	public List<Expenditure> findByUserId(String userId);
//	 
//	public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate);
//	
//	public List<Expenditure> findByUserIdAndMonth(String userId, int month);
//	 
//	public List<Expenditure> findByUserIdAndYear(String userId, int year);
//	
//	public List<Expenditure> findByUserIdAndExpenditureTag(String userId, String expenditureTag);
	
	//Update
	public Expenditure update(int expenditureId, Expenditure expenditure, String categoryTag) throws ExpenditureNotFoundException, CategoryNotFoundException;
	
	//Delete
	public void deleteByExpenditureId(int expenditureId);
	
}
