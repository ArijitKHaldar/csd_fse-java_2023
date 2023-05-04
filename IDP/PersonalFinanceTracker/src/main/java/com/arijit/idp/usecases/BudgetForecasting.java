package com.arijit.idp.usecases;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
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
public class BudgetForecasting {

	@Autowired
	private IncomeServiceImpl incomeService;

	@Autowired
	private ExpenditureServiceImpl expenditureService;

	public double predictSavings(String userId, Date currentDate) throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		List<Expenditure> expenditures;
		List<Income> incomes;

		LocalDate localDate = currentDate.toLocalDate();
		int currentMonth = localDate.getMonth().getValue();
		int currentYear = localDate.getYear();

		expenditures = expenditureService.findByUserIdAndYear(userId, currentYear);

		incomes = incomeService.findByUserIdAndYear(userId, currentYear);

		double totalIncome = 0;
		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		double totalExpenditure = 0;
		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double avgIncome = totalIncome / currentMonth;
		double avgExpenditure = totalExpenditure / currentMonth;

		double predictedSavingsCurrent = avgIncome - avgExpenditure;
		double predictedSavingsRemaining = predictedSavingsCurrent * (12 - currentMonth);
		double predictedYearlySavings = predictedSavingsRemaining + predictedSavingsCurrent;
		double predictedMonthlySavings = predictedYearlySavings / 12;

		if (predictedMonthlySavings < 0) {
			throw new InvalidDataFormatException("Cannot predict. Some error occured");
		}

		DecimalFormat df = new DecimalFormat("#.##");
		double result = Double.parseDouble(df.format(predictedMonthlySavings));
		return result;
	}
}
