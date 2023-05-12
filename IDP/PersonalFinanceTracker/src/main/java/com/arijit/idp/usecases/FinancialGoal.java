package com.arijit.idp.usecases;

import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.NoSuchElementException;

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
public class FinancialGoal {

	@Autowired
	private IncomeServiceImpl incomeService;

	@Autowired
	private ExpenditureServiceImpl expenditureService;

	public double calculateSavingsCompletion(String userId, Date date, double savingsGoalAmount)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException,
			ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Calculate percentage completion of financial goal for user: {}", userId);
		LocalDate enteredDate = date.toLocalDate();
		LocalDate currentDate = LocalDate.now();
		int currentYear = LocalDate.now().getYear();
		long daysLeft = ChronoUnit.DAYS.between(currentDate, enteredDate);

		List<Expenditure> expenditures;
		List<Income> incomes;

		incomes = incomeService.findByUserIdAndYear(userId, currentYear);
		expenditures = expenditureService.findByUserIdAndYear(userId, currentYear);

		Date earliestDate = incomes.stream().map(Income::getIncomeDate).min(Date::compareTo)
				.orElseThrow(NoSuchElementException::new);
		Date latestDate = incomes.stream().map(Income::getIncomeDate).max(Date::compareTo)
				.orElseThrow(NoSuchElementException::new);

		long daysBetweenIncomes = ChronoUnit.DAYS.between(earliestDate.toLocalDate(), latestDate.toLocalDate());

		earliestDate = expenditures.stream().map(Expenditure::getExpenditureDate).min(Date::compareTo)
				.orElseThrow(NoSuchElementException::new);
		latestDate = expenditures.stream().map(Expenditure::getExpenditureDate).max(Date::compareTo)
				.orElseThrow(NoSuchElementException::new);

		long daysBetweenExpenditures = ChronoUnit.DAYS.between(earliestDate.toLocalDate(), latestDate.toLocalDate());

		double totalIncome = 0;
		for (Income income : incomes) {
			totalIncome += income.getIncomeAmount().doubleValue();
		}

		double totalExpenditure = 0;
		for (Expenditure expenditure : expenditures) {
			totalExpenditure += expenditure.getExpenditureAmount().doubleValue();
		}

		double avgIncome = 0;
		double avgExpenditure = 0;
		if (daysBetweenIncomes > 0 && daysBetweenExpenditures > 0) {
			avgIncome = totalIncome / daysBetweenIncomes;
			avgExpenditure = totalExpenditure / daysBetweenExpenditures;
		} else {
			throw new InvalidDataFormatException("Not enough data in database to run predictions");
		}
		double totalIncomeBetweenDates = avgIncome * daysLeft;
		double totalExpenditureBetweenDates = avgExpenditure * daysLeft;
		double remainingSavings = Math.max(totalIncomeBetweenDates - totalExpenditureBetweenDates, 0);

		double savingsCompletionPercentage = remainingSavings / savingsGoalAmount * 100;
		savingsCompletionPercentage = Math.min(savingsCompletionPercentage, 100);

		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(savingsCompletionPercentage));
	}

}