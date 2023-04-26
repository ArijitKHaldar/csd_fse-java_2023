package com.arijit.idp.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.repository.ExpenditureRepository;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {

	@Autowired
	private ExpenditureRepository expenditureRepository;

	// Create
	@Override
	public Expenditure create(Expenditure expenditure) throws ExpenditureAlreadyPresentException {
		if(expenditureRepository.existsById(expenditure.getExpenditureId())) {
			throw new ExpenditureAlreadyPresentException();
		}
		Expenditure savedExpenditure = expenditureRepository.save(expenditure);
		return savedExpenditure;
	}

	// Retrieve
	@Override
	public List<Expenditure> findByUserId(String userId) throws ExpenditureNotFoundException {
		List<Expenditure> expendituresByUserId = expenditureRepository.findByUserId(userId);
		if(expendituresByUserId.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserId;
	}

	@Override
	public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate) throws ExpenditureNotFoundException {
		List<Expenditure> expendituresByUserIdAndDate = expenditureRepository.findByUserIdAndExpenditureDate(userId, expenditureDate);
		if(expendituresByUserIdAndDate.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndDate;
	}

	@Override
	public List<Expenditure> findByUserIdAndMonth(String userId, int month) throws ExpenditureNotFoundException {
		List<Expenditure> expendituresByUserIdAndMonth = expenditureRepository.findByUserIdAndMonth(userId, month);
		if(expendituresByUserIdAndMonth.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndMonth;
	}

	@Override
	public List<Expenditure> findByUserIdAndYear(String userId, int year) throws ExpenditureNotFoundException {
		List<Expenditure> expendituresByUserIdAndYear = expenditureRepository.findByUserIdAndYear(userId, year);
		if(expendituresByUserIdAndYear.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndYear;
	}

	@Override
	public List<Expenditure> findByUserIdAndExpenditureType(String userId, String expenditureTag) throws ExpenditureNotFoundException, CategoryNotFoundException {
		if(expenditureRepository.findByUserId(userId).isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		List<Expenditure> expenditure = expenditureRepository.findByUserIdAndCategory(userId, expenditureTag);
		if(expenditure.isEmpty()) {
			throw new CategoryNotFoundException();
		}
		return expenditure;
	}

	@Override
	public Expenditure update(int expenditureId, Expenditure expenditure) throws ExpenditureNotFoundException {
		Optional<Expenditure> existingExpenditure = expenditureRepository.findById(expenditureId);
		if (!existingExpenditure.isPresent()) {
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
	public void delete(int expenditureId) throws ExpenditureNotFoundException {
		Expenditure expenditure = expenditureRepository.findById(expenditureId).orElseThrow(
				() -> new ExpenditureNotFoundException("Expenditure not found " + "with expenditure id " + expenditureId));
		expenditureRepository.delete(expenditure);
	}

}
