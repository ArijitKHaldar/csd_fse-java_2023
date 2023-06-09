package com.arijit.idp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.IncomeAlreadyPresentException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.repository.IncomeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private IncomeRepository incomeRepository;

	private static final String ERROR_MSG = "User Id cannot be empty";

	// Create
	@Override
	public Income insertIncome(Income income) throws IncomeAlreadyPresentException, InvalidDataFormatException {

		log.info("Inserting income {}", income);
		if (incomeRepository.existsById(income.getIncomeId())) {
			throw new IncomeAlreadyPresentException();
		}
		if (income.getIncomeAmount().doubleValue() < 0) {
			throw new InvalidDataFormatException("Income cannot be negative!");
		}
		return incomeRepository.save(income);
	}

//	// Retrieve
	@Override
	public List<Income> findByUserId(String userId)
			throws IncomeNotFoundException, NullValueEnteredException, NotAStringException {

		log.info("Finding incomes for user {}", userId);
		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException(ERROR_MSG);
		}
		List<Income> incomeByUserId = incomeRepository.findByUserId(userId);
		if (incomeByUserId.isEmpty()) {
			throw new IncomeNotFoundException();
		}
		return incomeByUserId;
	}

	@Override
	public List<Income> findByUserIdAndMonth(String userId, int month)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Finding incomes for user {} and month {}", userId, month);

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException(ERROR_MSG);
		}
		if ((!(Integer.valueOf(month) instanceof Integer)) || month < 1 || month > 12) {
			throw new InvalidDataFormatException("Month should be between 1 to 12");
		}
		List<Income> incomeByUserIdAndMonth = incomeRepository.findByUserIdAndMonth(userId, month);
		if (incomeByUserIdAndMonth.isEmpty()) {
			throw new IncomeNotFoundException();
		}
		return incomeByUserIdAndMonth;
	}

	@Override
	public List<Income> findByUserIdAndYear(String userId, int year)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Finding incomes for user {} and year {}", userId, year);
		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException(ERROR_MSG);
		}
		if ((!(Integer.valueOf(year) instanceof Integer)) || year < 1000 || year > 9999) {
			throw new InvalidDataFormatException("Invalid year entered");
		}

		List<Income> incomeByUserIdAndYear = incomeRepository.findByUserIdAndYear(userId, year);
		if (incomeByUserIdAndYear.isEmpty()) {
			throw new IncomeNotFoundException();
		}
		return incomeByUserIdAndYear;
	}

	// Update
	@Override
	public Income updateByIncomeId(int incomeId, Income updatedIncome)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Updating income with incomeId: {}", incomeId);
		if (incomeId == 0) {
			throw new NullValueEnteredException("Income Id cannot be empty");
		}
		Optional<Income> existingIncome = incomeRepository.findById(incomeId);
		Income newIncome = null;
		if (existingIncome.isPresent()) {
			newIncome = existingIncome.get();
			newIncome.setIncomeDate(updatedIncome.getIncomeDate());
			newIncome.setIncomeAmount(updatedIncome.getIncomeAmount());
			incomeRepository.save(newIncome);
		} else {
			throw new IncomeNotFoundException("No income found with given income id");
		}
		return newIncome;
	}

	// Delete
	@Override
	public void deleteByIncomeId(int incomeId)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Deleting income with incomeId: {}", incomeId);
		if (incomeId == 0) {
			throw new NullValueEnteredException("Income Id cannot be empty");
		}
		Optional<Income> existingIncome = incomeRepository.findById(incomeId);
		if (existingIncome.isPresent()) {
			incomeRepository.deleteById(incomeId);
		} else {
			throw new IncomeNotFoundException("No income found with given income id");
		}
	}

}
