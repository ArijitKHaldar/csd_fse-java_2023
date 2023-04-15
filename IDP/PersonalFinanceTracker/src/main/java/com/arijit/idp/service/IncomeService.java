package com.arijit.idp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Income;
import com.arijit.idp.repository.IncomeRepository;

@Service
public class IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	// Create
	public void insertIncome(Income income) {
		incomeRepository.save(income);
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
	public String updateByIncomeId(Income income) {
		Income existingIncome = incomeRepository.findById(income.getIncomeId()).orElse(null);
		String status = "Income could not be found";
		if (existingIncome != null) {
			existingIncome.setIncomeAmount(income.getIncomeAmount());
			existingIncome.setIncomeDate(income.getIncomeDate());
			incomeRepository.save(existingIncome);
			status = "Income updated successfully";
		}
		return status;
	}

	// Delete
	public String deleteByIncomeId(int incomeId) {
		incomeRepository.deleteById(incomeId);
		return ("Income removed for Income Id: " + incomeId);
	}

}
