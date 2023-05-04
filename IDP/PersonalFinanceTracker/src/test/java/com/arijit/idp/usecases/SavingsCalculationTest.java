package com.arijit.idp.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
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

public class SavingsCalculationTest {

	@Mock
	private IncomeServiceImpl incomeService;

	@Mock
	private ExpenditureServiceImpl expenditureService;

	@InjectMocks
	private SavingsCalculation savingsCalculation;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCalculateMonthlySavingsPercentage() throws Exception {
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure("expenditure1", 1, Date.valueOf("2023-04-02"), new BigDecimal("100")));
		expenditures.add(new Expenditure("expenditure2", 2, Date.valueOf("2023-04-12"), new BigDecimal("200")));

		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income("income1", Date.valueOf("2023-04-02"), new BigDecimal("500")));
		incomes.add(new Income("income2", Date.valueOf("2023-04-12"), new BigDecimal("300")));

		when(expenditureService.findByUserIdAndMonth(anyString(), anyInt())).thenReturn(expenditures);
		when(incomeService.findByUserIdAndMonth(anyString(), anyInt())).thenReturn(incomes);

		double expectedSavingsPercentage = 62.5;
		double actualSavingsPercentage = savingsCalculation.calculateMonthlySavingsPercentage("userId", 4);

		assertEquals(expectedSavingsPercentage, actualSavingsPercentage);
		verify(expenditureService).findByUserIdAndMonth("userId", 4);
		verify(incomeService).findByUserIdAndMonth("userId", 4);
	}

	@Test
    public void testCalculateMonthlySavingsPercentage_WithInvalidDataFormatException() throws Exception {
        when(expenditureService.findByUserIdAndMonth(anyString(), anyInt())).thenThrow(new InvalidDataFormatException("Data entered has some problems"));

        assertThrows(InvalidDataFormatException.class, () -> savingsCalculation.calculateMonthlySavingsPercentage("userId", 1));

        verify(expenditureService).findByUserIdAndMonth("userId", 1);
    }

	@Test
	public void testCalculateYearlySavingsPercentage() throws Exception {
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure("user1", 1, Date.valueOf("2023-04-02"), new BigDecimal("100")));
		expenditures.add(new Expenditure("user1", 2, Date.valueOf("2023-04-12"), new BigDecimal("200")));

		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income("user1", Date.valueOf("2023-04-02"), new BigDecimal("500")));
		incomes.add(new Income("user1", Date.valueOf("2023-04-12"), new BigDecimal("300")));

		when(expenditureService.findByUserIdAndYear(anyString(), anyInt())).thenReturn(expenditures);
		when(incomeService.findByUserIdAndYear(anyString(), anyInt())).thenReturn(incomes);

		double expectedSavingsPercentage = 62.5;
		double actualSavingsPercentage = savingsCalculation.calculateYearlySavingsPercentage("userId", 2023);

		assertEquals(expectedSavingsPercentage, actualSavingsPercentage);
		verify(expenditureService).findByUserIdAndYear("userId", 2023);
		verify(incomeService).findByUserIdAndYear("userId", 2023);
	}

	@Test
    public void testCalculateYearlySavingsPercentage_WithInvalidDataFormatException() throws Exception {
        when(expenditureService.findByUserIdAndYear(anyString(), anyInt())).thenThrow(new InvalidDataFormatException("Data entered has some problems"));

        assertThrows(InvalidDataFormatException.class, () -> savingsCalculation.calculateYearlySavingsPercentage("userId", 202));

        verify(expenditureService).findByUserIdAndYear("userId", 202);
    }

	@Test
	public void testCalculateMonthlySavingsPercentage_NoExpenditureFound() throws Exception {
		when(expenditureService.findByUserIdAndMonth(anyString(), anyInt())).thenThrow(new ExpenditureNotFoundException("Expenditure not found"));
		
		assertThrows(ExpenditureNotFoundException.class, () -> savingsCalculation.calculateMonthlySavingsPercentage("userId", 2023));
		
		verify(expenditureService).findByUserIdAndMonth("userId", 2023);
	}

	@Test
	public void testCalculateMonthlySavingsPercentage_NoIncomeFound() throws Exception {
		when(incomeService.findByUserIdAndMonth(anyString(), anyInt())).thenThrow(new IncomeNotFoundException("Income not found"));
		
		assertThrows(IncomeNotFoundException.class, () -> savingsCalculation.calculateMonthlySavingsPercentage("userId", 2023));
		
		verify(incomeService).findByUserIdAndMonth("userId", 2023);
	}

	@Test
	public void testCalculateYearlySavingsPercentage_NoExpenditureFound() throws Exception {
		when(expenditureService.findByUserIdAndYear(anyString(), anyInt())).thenThrow(new ExpenditureNotFoundException("Expenditure not found"));
		
		assertThrows(ExpenditureNotFoundException.class, () -> savingsCalculation.calculateYearlySavingsPercentage("userId", 2023));
		
		verify(expenditureService).findByUserIdAndYear("userId", 2023);
	}

	@Test
	public void testCalculateYearlySavingsPercentage_NoIncomeFound() throws Exception {
		when(incomeService.findByUserIdAndYear(anyString(), anyInt())).thenThrow(new IncomeNotFoundException("Income not found"));
		
		assertThrows(IncomeNotFoundException.class, () -> savingsCalculation.calculateYearlySavingsPercentage("userId", 2023));
		
		verify(incomeService).findByUserIdAndYear("userId", 2023);
	}

	@Test
	public void testExceptionSituations() throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure("user1", 1, Date.valueOf("2023-04-02"), new BigDecimal("500")));
		expenditures.add(new Expenditure("user1", 2, Date.valueOf("2023-04-12"), new BigDecimal("300")));

		List<Income> incomes = new ArrayList<>();
		incomes.add(new Income("user1", Date.valueOf("2023-04-02"), new BigDecimal("100")));
		incomes.add(new Income("user1", Date.valueOf("2023-04-12"), new BigDecimal("200")));

		when(expenditureService.findByUserIdAndYear(anyString(), anyInt())).thenReturn(expenditures);
		when(incomeService.findByUserIdAndYear(anyString(), anyInt())).thenReturn(incomes);

		assertThrows(InvalidDataFormatException.class,
				() -> savingsCalculation.calculateYearlySavingsPercentage("userId", 2023));

		when(expenditureService.findByUserIdAndMonth(anyString(), anyInt())).thenReturn(expenditures);
		when(incomeService.findByUserIdAndMonth(anyString(), anyInt())).thenReturn(incomes);

		assertThrows(InvalidDataFormatException.class,
				() -> savingsCalculation.calculateMonthlySavingsPercentage("userId", 4));
	}
}
