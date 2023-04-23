package com.arijit.idp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.arijit.idp.entity.Category;
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

	// Create
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Expenditure create(Expenditure expenditure) {
		Expenditure savedExpenditure = expenditureRepository.save(expenditure);
		return savedExpenditure;
	}
	
	// Retrieve
	@Override
    public List<Expenditure> findByUserId(String userId) {
        List<Expenditure> expendituresByUserId = expenditureRepository.findByUserId(userId);
        return expendituresByUserId;
    }
	
	@Override
    public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate) {
        return expenditureRepository.findByUserIdAndExpenditureDate(userId, expenditureDate);
    }
	
	@Override
    public List<Expenditure> findByUserIdAndMonth(String userId, int month) {
        return expenditureRepository.findByUserIdAndMonth(userId, month);
    }
	
	@Override
    public List<Expenditure> findByUserIdAndYear(String userId, int year) {
        return expenditureRepository.findByUserIdAndYear(userId, year);
    }
	
	@Override
	public List<Expenditure> findByUserIdAndExpenditureType(String userId, String expenditureTag) {
		List<Expenditure> expenditure = expenditureRepository.findByUserIdAndCategory(userId, expenditureTag);
	    return expenditure;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Expenditure update(int expenditureId, Expenditure expenditure) throws ExpenditureNotFoundException {
		Optional<Expenditure> existingExpenditure = expenditureRepository.findById(expenditureId);
		if(!existingExpenditure.isPresent()) {
			throw new ExpenditureNotFoundException("Expenditure not found with id: " + expenditureId);
		}
		Expenditure updatedExpenditure = existingExpenditure.get();
		updatedExpenditure.setExpenditureDate(expenditure.getExpenditureDate());
		updatedExpenditure.setExpenditureAmount(expenditure.getExpenditureAmount());
		updatedExpenditure.setCategoryId(expenditure.getCategoryId());
		expenditureRepository.save(updatedExpenditure);
		
		return updatedExpenditure;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void delete(int expenditureId) {
		Expenditure expenditure = expenditureRepository.findById(expenditureId)
				.orElseThrow(() -> new EntityNotFoundException("Expenditure not found "
						+ "with expenditure id " + expenditureId));
		expenditureRepository.delete(expenditure);
	}

}
