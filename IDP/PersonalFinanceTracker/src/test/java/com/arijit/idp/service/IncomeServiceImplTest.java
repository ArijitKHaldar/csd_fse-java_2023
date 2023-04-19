package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Income;
import com.arijit.idp.repository.IncomeRepository;

@ExtendWith(MockitoExtension.class)
public class IncomeServiceImplTest {

	@InjectMocks
    private IncomeServiceImpl incomeServiceImpl;

    @Mock
    private IncomeRepository incomeRepository;

    @Test
    public void testInsertIncome() {
        Income income = new Income();
        incomeServiceImpl.insertIncome(income);
        verify(incomeRepository).save(income);
    }

    @Test
    public void testFindByUserId() {
        String userId = "user123";
        List<Income> expectedIncomes = new ArrayList<>();
        when(incomeRepository.findByUserId(userId)).thenReturn(expectedIncomes);
        List<Income> actualIncomes = incomeServiceImpl.findByUserId(userId);
        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test
    public void testFindByUserIdAndMonth() {
        String userId = "user123";
        int month = 2;
        List<Income> expectedIncomes = new ArrayList<>();
        when(incomeRepository.findByUserIdAndMonth(userId, month)).thenReturn(expectedIncomes);
        List<Income> actualIncomes = incomeServiceImpl.findByUserIdAndMonth(userId, month);
        assertEquals(expectedIncomes, actualIncomes);
    }

    @Test
    public void testFindByUserIdAndYear() {
        String userId = "user123";
        int year = 2022;
        List<Income> expectedIncomes = new ArrayList<>();
        when(incomeRepository.findByUserIdAndYear(userId, year)).thenReturn(expectedIncomes);
        List<Income> actualIncomes = incomeServiceImpl.findByUserIdAndYear(userId, year);
        assertEquals(expectedIncomes, actualIncomes);
    }
    
    @Test
    @Disabled
    public void testUpdateByIncomeId() {
        Income updatedIncome = new Income();
        updatedIncome = incomeRepository.findById(1).get();
        updatedIncome.setIncomeAmount(new BigDecimal("80000"));
        updatedIncome.setIncomeDate(Date.valueOf("2022-02-15"));

        String status = incomeServiceImpl.updateByIncomeId(1, updatedIncome);

        assertEquals("Income updated successfully", status);

        List<Income> incomeList = incomeServiceImpl.findByUserIdAndMonth("user123", 2);

        assertEquals(1, incomeList.size());

        Income income = incomeList.get(0);
        assertEquals(new BigDecimal("80000"), income.getIncomeAmount());
        assertEquals(Date.valueOf("2022-02-15"), income.getIncomeDate());
    }

    @Test
    public void testDeleteByIncomeId() {
        int incomeId = 1;
        when(incomeRepository.findById(incomeId)).thenReturn(Optional.of(new Income()));
        String actualStatus = incomeServiceImpl.deleteByIncomeId(incomeId);
        verify(incomeRepository).deleteById(incomeId);
        assertEquals("Income removed for Income Id: " + incomeId, actualStatus);
    }
}
