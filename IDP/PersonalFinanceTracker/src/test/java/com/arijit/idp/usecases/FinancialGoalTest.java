package com.arijit.idp.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.ExpenditureServiceImpl;
import com.arijit.idp.service.IncomeServiceImpl;

public class FinancialGoalTest {

	@Mock
	private IncomeServiceImpl incomeService;

	@Mock
	private ExpenditureServiceImpl expenditureService;

	@InjectMocks
	private FinancialGoal financialGoal;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Calculate Savings Completion Test")
	void testCalculateSavingsCompletion() throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {
		String userId = "testUser";
		Date date = Date.valueOf(LocalDate.now().plusDays(30));
		double savingsGoalAmount = 8000;

		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income(userId, Date.valueOf("2023-01-02"), new BigDecimal("5000")));
		incomes.add(new Income(userId, Date.valueOf("2023-02-12"), new BigDecimal("7000")));
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure(userId, 1, Date.valueOf("2023-01-02"), new BigDecimal("2500")));
		expenditures.add(new Expenditure(userId, 2, Date.valueOf("2023-02-12"), new BigDecimal("3500")));

		when(incomeService.findByUserIdAndYear(userId, 2023)).thenReturn(incomes);
		when(expenditureService.findByUserIdAndYear(userId, 2023)).thenReturn(expenditures);

		double savingsCompletionPercentage = financialGoal.calculateSavingsCompletion(userId, date, savingsGoalAmount);

		assertEquals(54.88, savingsCompletionPercentage, "Savings completion percentage is incorrect");
	}
}