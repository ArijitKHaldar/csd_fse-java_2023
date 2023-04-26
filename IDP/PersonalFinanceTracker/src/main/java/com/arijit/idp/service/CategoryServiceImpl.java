package com.arijit.idp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// Create
	@Override
	public Category create(Category category) throws CategoryAlreadyPresentException {
		
		List<Category> categories = categoryRepository.findAll();
	    for (Category existingCategory : categories) {
	        if (existingCategory.getExpenditureTag().equals(category.getExpenditureTag())) {
	            throw new CategoryAlreadyPresentException("Category with name " + category.getExpenditureTag() + " already exists");
	        }
	    }
		
		Category newCategory = categoryRepository.save(category);
		return newCategory;
	}

	// Retrieve
	@Override
	public List<Category> findAllExpenditureTags() throws CategoryNotFoundException {
		List<Category> category = categoryRepository.findAll();
		return category;
	}

	// Delete
	@Override
	public void deleteByExpenditureTag(String expenditureTag) throws CategoryNotFoundException {
		Category category = categoryRepository.findByExpenditureTagIgnoreCase(expenditureTag);
		categoryRepository.delete(category);
	}
}
