package com.arijit.idp.service;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryNotFoundException;

public interface CategoryService {

	//Create
	public Category create(Category category);
	
	//Retrieve
	public Category findByExpenditureId(int expenditureId);
	
	//Update
	public void updateCategory(Category category) throws CategoryNotFoundException;
	
	//Delete
	public void deleteByExpenditureId(int expenditureId);
}
