package com.arijit.idp.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
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

public class BudgetForecastingTest {

	@Mock
	private IncomeServiceImpl incomeService;

	@Mock
	private ExpenditureServiceImpl expenditureService;

	@InjectMocks
	private BudgetForecasting mockedService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testPredictSavings() throws Exception {
		String userId = "user1";
		LocalDate currentDate = LocalDate.now();
		Date date = Date.valueOf(currentDate);
		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income(userId, date, new BigDecimal("1000")));
		incomes.add(new Income(userId, date, new BigDecimal("1500")));
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure(userId, 1, date, new BigDecimal("500")));
		expenditures.add(new Expenditure(userId, 2, date, new BigDecimal("300")));
		when(incomeService.findByUserIdAndYear(userId, currentDate.getYear())).thenReturn(incomes);
		when(expenditureService.findByUserIdAndYear(userId, currentDate.getYear())).thenReturn(expenditures);

		double predictedSavings = mockedService.predictSavings(userId);

		assertEquals(408, predictedSavings, 0.001);
	}

	@Test
	void testPredictSavings_noExpenditureFound() throws Exception {
		String userId = "user1";
		LocalDate currentDate = LocalDate.now();
		Date date = Date.valueOf(currentDate);
		when(expenditureService.findByUserIdAndYear(userId, currentDate.getYear()))
				.thenThrow(new ExpenditureNotFoundException("No expenditure found"));

		ExpenditureNotFoundException thrown = org.junit.jupiter.api.Assertions
				.assertThrows(ExpenditureNotFoundException.class, () -> {
					mockedService.predictSavings(userId);
				});
		assertEquals("No expenditure found", thrown.getMessage());
	}

	@Test
	void testPredictSavings_noIncomeFound() throws Exception {
		String userId = "user1";
		LocalDate currentDate = LocalDate.now();
		Date date = Date.valueOf(currentDate);
		when(incomeService.findByUserIdAndYear(userId, currentDate.getYear()))
				.thenThrow(new IncomeNotFoundException("No income found"));

		IncomeNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(IncomeNotFoundException.class,
				() -> {
					mockedService.predictSavings(userId);
				});
		assertEquals("No income found", thrown.getMessage());
	}

	@Test
	void testExceptionCase() throws NullValueEnteredException, NotAStringException, InvalidDataFormatException,
			IncomeNotFoundException, ExpenditureNotFoundException {
		String userId = "user1";
		LocalDate currentDate = LocalDate.now();
		Date date = Date.valueOf(currentDate);
		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income(userId, Date.valueOf("2023-01-02"), new BigDecimal("500")));
		incomes.add(new Income(userId, Date.valueOf("2023-01-12"), new BigDecimal("300")));
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure(userId, 1, Date.valueOf("2023-01-02"), new BigDecimal("1000")));
		expenditures.add(new Expenditure(userId, 2, Date.valueOf("2023-01-12"), new BigDecimal("1500")));
		when(incomeService.findByUserIdAndYear(userId, currentDate.getYear())).thenReturn(incomes);
		when(expenditureService.findByUserIdAndYear(userId, currentDate.getYear())).thenReturn(expenditures);

		assertThrows(InvalidDataFormatException.class, () -> mockedService.predictSavings(userId));

	}
}
