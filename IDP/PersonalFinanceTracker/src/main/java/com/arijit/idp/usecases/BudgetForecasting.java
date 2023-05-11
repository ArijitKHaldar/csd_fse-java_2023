package com.arijit.idp.usecases;

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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class BudgetForecasting {

	@Autowired
	private IncomeServiceImpl incomeService;

	@Autowired
	private ExpenditureServiceImpl expenditureService;

	public double predictSavings(String userId) throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Predict savings for User: {}", userId);
		List<Expenditure> expenditures;
		List<Income> incomes;

		LocalDate localDate = LocalDate.now();
		int currentMonth = localDate.getMonth().getValue();
		int currentYear = localDate.getYear();

		expenditures = expenditureService.findByUserIdAndYear(userId, currentYear);

		incomes = incomeService.findByUserIdAndYear(userId, currentYear);

		double totalIncome = 0;
		double totalExpenditure = 0;

		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double avgIncome = totalIncome / currentMonth;
		double avgExpenditure = totalExpenditure / currentMonth;

		double savings = totalIncome - totalExpenditure;
		double predictedSavings = savings + avgIncome - avgExpenditure;

		if (predictedSavings < 0) {
			throw new InvalidDataFormatException("Cannot predict. Some error occured");
		}

		double predictedMonthlySavings = predictedSavings / currentMonth;

		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(predictedMonthlySavings));
	}
}
