package com.arijit.idp.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arijit.idp.entity.Category;
import com.arijit.idp.entity.Category.ExpenditureTag;
import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.repository.ExpenditureRepository;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {

	@Autowired
	private ExpenditureRepository expenditureRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Expenditure create(Expenditure expenditure, Category category) {
		// Save expenditure
		Expenditure savedExpenditure = expenditureRepository.save(expenditure);

		// Save category
		category.setExpenditureId(savedExpenditure.getExpenditureId());
		categoryService.create(category);

		return savedExpenditure;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Expenditure update(int expenditureId, Expenditure expenditure, String categoryTag) throws ExpenditureNotFoundException, CategoryNotFoundException {
		Optional<Expenditure> existingExpenditure = expenditureRepository.findById(expenditureId);
		if(!existingExpenditure.isPresent()) {
			throw new ExpenditureNotFoundException("Expenditure not found with id: " + expenditureId);
		}
		Expenditure updatedExpenditure = existingExpenditure.get();
		updatedExpenditure.setExpenditureDate(expenditure.getExpenditureDate());
		updatedExpenditure.setExpenditureAmount(expenditure.getExpenditureAmount());
		expenditureRepository.save(updatedExpenditure);
		
		Category category = categoryService.findByExpenditureId(expenditureId);
		category.setExpenditureTag(ExpenditureTag.valueOf(categoryTag));
		categoryService.updateCategory(category);
		
		return updatedExpenditure;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void deleteByExpenditureId(int expenditureId) {
		Expenditure expenditure = expenditureRepository.findById(expenditureId)
				.orElseThrow(() -> new EntityNotFoundException("Expenditure not found "
						+ "with expenditure id " + expenditureId));
		categoryService.deleteByExpenditureId(expenditureId);
		expenditureRepository.delete(expenditure);
	}

}
