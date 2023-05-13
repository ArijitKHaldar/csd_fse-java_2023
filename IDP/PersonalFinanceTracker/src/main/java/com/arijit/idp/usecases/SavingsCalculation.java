package com.arijit.idp.usecases;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.ExpenditureServiceImpl;
import com.arijit.idp.service.IncomeServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SavingsCalculation {

	@Autowired
	private IncomeServiceImpl incomeService;

	@Autowired
	private ExpenditureServiceImpl expenditureService;

	private double calculateSavingsPercentage(List<Expenditure> expenditures, List<Income> incomes)
			throws InvalidDataFormatException {
		double totalIncome = 0;
		double savingsPercentage = 0;
		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		double totalExpenditure = 0;
		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double savings = totalIncome - totalExpenditure;
		if (totalIncome != 0) {
			savingsPercentage = (savings / totalIncome) * 100;
		}

		if (savingsPercentage <= 0) {
			throw new InvalidDataFormatException("Some error occured during calculations!");
		}

		return savingsPercentage;
	}

	public double calculateMonthlySavingsPercentage(String userId, int month) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Calculate monthly percentage savings for user: {}", userId);
		List<Expenditure> expenditures;
		List<Income> incomes;

		expenditures = expenditureService.findByUserIdAndMonth(userId, month);
		incomes = incomeService.findByUserIdAndMonth(userId, month);

		double savingsPercentage = calculateSavingsPercentage(expenditures, incomes);

		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(savingsPercentage));
	}

	public Double calculateYearlySavingsPercentage(String userId, int year) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Calculate yearly percentage savings for user: {}", userId);

		List<Expenditure> expenditures;
		List<Income> incomes;

		expenditures = expenditureService.findByUserIdAndYear(userId, year);
		incomes = incomeService.findByUserIdAndYear(userId, year);

		double savingsPercentage = calculateSavingsPercentage(expenditures, incomes);

		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(savingsPercentage));
	}
}
