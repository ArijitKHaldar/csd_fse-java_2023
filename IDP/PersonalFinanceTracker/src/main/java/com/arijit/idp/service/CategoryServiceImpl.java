package com.arijit.idp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	// Create
	@Override
	public Category create(Category category) throws CategoryAlreadyPresentException {

		log.info("Inserting category: {}", category);
		List<Category> categories = categoryRepository.findAll();
		for (Category existingCategory : categories) {
			if (existingCategory.getExpenditureTag().equalsIgnoreCase(category.getExpenditureTag())) {
				throw new CategoryAlreadyPresentException(
						"Category with name " + category.getExpenditureTag() + " already exists");
			}
		}
		category.setExpenditureTag(category.getExpenditureTag().toUpperCase());

		return categoryRepository.save(category);
	}

	// Retrieve
	@Override
	public List<Category> findAllExpenditureTags() throws CategoryNotFoundException {
		log.info("Find all categories");
		List<Category> category = categoryRepository.findAll();
		if (category.isEmpty()) {
			throw new CategoryNotFoundException();
		}
		return category;
	}

	// Delete
	@Override
	public void deleteByExpenditureTag(String expenditureTag) throws CategoryNotFoundException {

		log.info("Deleting by expenditure category: {}", expenditureTag);
		Category category = categoryRepository.findByExpenditureTagIgnoreCase(expenditureTag);
		if (category == null) {
			throw new CategoryNotFoundException();
		}
		categoryRepository.delete(category);
	}
}
