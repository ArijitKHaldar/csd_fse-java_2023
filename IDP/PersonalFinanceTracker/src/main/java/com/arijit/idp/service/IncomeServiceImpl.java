package com.arijit.idp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Income;
import com.arijit.idp.repository.IncomeRepository;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	// Create
	public Income insertIncome(Income income) {
		return incomeRepository.save(income);
	}

//	// Retrieve
	public List<Income> findByUserId(String userId) {
		List<Income> incomeByUserId = incomeRepository.findByUserId(userId);
		return incomeByUserId;
	}

	public List<Income> findByUserIdAndMonth(String userId, int month) {
		List<Income> incomeByUserIdAndMonth = incomeRepository.findByUserIdAndMonth(userId, month);
		return incomeByUserIdAndMonth;
	}

	public List<Income> findByUserIdAndYear(String userId, int year) {
		List<Income> incomeByUserIdAndYear = incomeRepository.findByUserIdAndYear(userId, year);
		return incomeByUserIdAndYear;
	}

	// Update
	public Income updateByIncomeId(int incomeId, Income updatedIncome) {
		Optional<Income> existingIncome = incomeRepository.findById(incomeId);
		Income newIncome = null;
		if (existingIncome.isPresent()) {
			newIncome = existingIncome.get();
			newIncome.setIncomeDate(updatedIncome.getIncomeDate());
			newIncome.setIncomeAmount(updatedIncome.getIncomeAmount());
			incomeRepository.save(newIncome);
		}
		return newIncome;
	}

	// Delete
	public String deleteByIncomeId(int incomeId) {
		String status;
		Optional<Income> existingIncome = incomeRepository.findById(incomeId);
		if(existingIncome.isPresent()) {
			incomeRepository.deleteById(incomeId);
			status = "Income removed for Income Id: " + incomeId;
		}
		else {
			status = "Income not found"; //Add exception handling
		}
		return status;
	}

}
