package com.arijit.idp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Category create(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category findByExpenditureId(int expenditureId) {
		return categoryRepository.findByExpenditureId(expenditureId);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void updateCategory(Category category) throws CategoryNotFoundException {
		Optional<Category> existingCategory = categoryRepository.findById(category.getCategoryId());
		if(!existingCategory.isPresent()) {
			throw new CategoryNotFoundException("Category not found with id: " + category.getCategoryId());
		}
		Category updatedCategory = existingCategory.get();
		updatedCategory.setExpenditureTag(category.getExpenditureTag());
		categoryRepository.save(updatedCategory);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByExpenditureId(int expenditureId) {
		Category category = categoryRepository.findByExpenditureId(expenditureId);
		categoryRepository.delete(category);
	}
}
