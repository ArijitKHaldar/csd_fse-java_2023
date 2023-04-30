package com.arijit.idp.usecases;

import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.ExpenditureServiceImpl;
import com.arijit.idp.service.IncomeServiceImpl;

@Configuration
public class SavingsCalculation {

	@Autowired
	private IncomeServiceImpl incomeService;

	@Autowired
	private ExpenditureServiceImpl expenditureService;

	public double calculateMonthlySavingsPercentage(String userId, int month) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		List<Expenditure> expenditures;
		List<Income> incomes;

		try {
			expenditures = expenditureService.findByUserIdAndMonth(userId, month);
		} catch (ExpenditureNotFoundException e) {
			throw new ExpenditureNotFoundException("No expenditure found");
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				throw new InvalidDataFormatException("Data entered has some problems");
			} else {
				throw e;
			}
		}

		try {
			incomes = incomeService.findByUserIdAndMonth(userId, month);
		} catch (IncomeNotFoundException e) {
			throw new IncomeNotFoundException("No income found");
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				throw new InvalidDataFormatException("Data entered has some problems");
			} else {
				throw e;
			}
		}

		double totalIncome = 0;
		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		double totalExpenditure = 0;
		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double savings = totalIncome - totalExpenditure;
		double savingsPercentage = (savings / totalIncome) * 100;

		if (savingsPercentage <= 0) {
			throw new InvalidDataFormatException("Some error occured during calculations!");
		}

		return savingsPercentage;
	}

	public Double calculateYearlySavingsPercentage(String userId, int year) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		List<Expenditure> expenditures;
		List<Income> incomes;

		try {
			expenditures = expenditureService.findByUserIdAndYear(userId, year);
		} catch (ExpenditureNotFoundException e) {
			throw new ExpenditureNotFoundException("No expenditure found");
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				throw new InvalidDataFormatException("Data entered has some problems");
			} else {
				throw e;
			}
		}
		try {
			incomes = incomeService.findByUserIdAndYear(userId, year);
		} catch (IncomeNotFoundException e) {
			throw new IncomeNotFoundException("No income found");
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				throw new InvalidDataFormatException("Data entered has some problems");
			} else {
				throw e;
			}
		}

		double totalIncome = 0;
		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		double totalExpenditure = 0;
		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double savings = totalIncome - totalExpenditure;
		double savingsPercentage = (savings / totalIncome) * 100;

		if (savingsPercentage <= 0) {
			throw new InvalidDataFormatException("Some error occured during calculations!");
		}

		DecimalFormat df = new DecimalFormat("#.##");
		double result = Double.parseDouble(df.format(savingsPercentage));
		return result;

	}
}
