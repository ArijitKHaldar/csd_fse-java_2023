package com.arijit.idp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arijit.idp.entity.Category;
import com.arijit.idp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// Create
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW) //Remove it and test
	public Category create(Category category) {
		Category newCategory = categoryRepository.save(category);
		return newCategory;
	}

	// Retrieve
	@Override
	public List<Category> findAllExpenditureTags() {
		List<Category> category = categoryRepository.findAll();
		return category;
	}

	// Delete
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByExpenditureTag(String expenditureTag) {
		Category category = categoryRepository.findByExpenditureTagIgnoreCase(expenditureTag);
		categoryRepository.delete(category);
	}
}
