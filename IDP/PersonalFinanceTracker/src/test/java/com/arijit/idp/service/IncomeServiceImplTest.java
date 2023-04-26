package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Income;
import com.arijit.idp.entity.Login;
import com.arijit.idp.repository.IncomeRepository;

@ExtendWith(MockitoExtension.class)
public class IncomeServiceImplTest {

	@InjectMocks
	private IncomeServiceImpl incomeServiceImpl;

	@Mock
	private IncomeRepository mockIncomeRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testInsertIncome() {

		Login mockLogin = new Login();
		mockLogin.setEmailId("test@example.com");
		mockLogin.setPassword("testpass");
		mockLogin.setUserId("testuser");

		Income mockIncome = new Income("testuser", Date.valueOf("2023-01-01"), new BigDecimal(20000));
		mockIncome.setLogin(mockLogin);
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		incomeServiceImpl.insertIncome(mockIncome);
		verify(mockIncomeRepository).save(mockIncome);

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncome = incomeServiceImpl.findByUserId("testuser");
		assertEquals(mockIncome, testIncome.get(0));
	}

	@Test
	public void testFindByUserId() {

		String userId = "testuser";
		Income mockIncome = new Income(userId, Date.valueOf("2023-01-01"), new BigDecimal(20000));
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserId(userId)).thenReturn(incomeList);
		List<Income> testIncomeTrue = incomeServiceImpl.findByUserId(userId);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		List<Income> testIncomeFalse = incomeServiceImpl.findByUserId("newuserid");
		assertFalse(testIncomeFalse.size() > 0);
	}

	@Test
	public void testFindByUserIdAndMonth() {
		String userId = "testuser";
		int month = 2;
		Income mockIncome = new Income(userId, Date.valueOf("2023-02-01"), new BigDecimal(20000));
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserIdAndMonth(userId, month)).thenReturn(incomeList);
		List<Income> testIncomeTrue = incomeServiceImpl.findByUserIdAndMonth(userId, month);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		List<Income> testIncomeFalse = incomeServiceImpl.findByUserIdAndMonth("newuserid", month);
		assertFalse(testIncomeFalse.size() > 0);

		testIncomeFalse = incomeServiceImpl.findByUserIdAndMonth(userId, 6);
		assertFalse(testIncomeFalse.size() > 0);
	}

	@Test
	public void testFindByUserIdAndYear() {
		String userId = "testuser";
		int year = 2023;
		Income mockIncome = new Income(userId, Date.valueOf("2023-02-01"), new BigDecimal(20000));
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserIdAndYear(userId, year)).thenReturn(incomeList);
		List<Income> testIncomeTrue = incomeServiceImpl.findByUserIdAndYear(userId, year);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		List<Income> testIncomeFalse = incomeServiceImpl.findByUserIdAndYear("newuserid", year);
		assertFalse(testIncomeFalse.size() > 0);

		testIncomeFalse = incomeServiceImpl.findByUserIdAndYear(userId, 2020);
		assertFalse(testIncomeFalse.size() > 0);
	}

	@Test
	public void testUpdateByIncomeId() {
		Income mockIncome = new Income("testuser", Date.valueOf("2023-01-01"), new BigDecimal(20000));
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncomeTrue = incomeServiceImpl.findByUserId("testuser");
		assertEquals(mockIncome, testIncomeTrue.get(0));

		Income mockIncomeUpdate = new Income("testuser", Date.valueOf("2023-01-01"), new BigDecimal(200));
		try {
			field = mockIncomeUpdate.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncomeUpdate, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		when(mockIncomeRepository.findById(1)).thenReturn(Optional.of(mockIncomeUpdate));
		when(mockIncomeRepository.save(mockIncomeUpdate)).thenReturn(mockIncomeUpdate);
		Income updatedIncome = incomeServiceImpl.updateByIncomeId(1, mockIncomeUpdate);
		verify(mockIncomeRepository, times(1)).save(mockIncomeUpdate);
		assertEquals(mockIncomeUpdate, updatedIncome);
		assertNotEquals(mockIncomeUpdate, mockIncome);
	}

	@Test
	public void testDeleteByIncomeId() {

		int incomeId = 1;

		Income mockIncome = new Income("testuser", Date.valueOf("2023-02-01"), new BigDecimal(20000));
		Field field = null;
		try {
			field = mockIncome.getClass().getDeclaredField("incomeId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(mockIncome, incomeId);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockIncomeRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncomeTrue = incomeServiceImpl.findByUserId("testuser");
		assertEquals(1, testIncomeTrue.size());
		assertEquals(mockIncome, testIncomeTrue.get(0));

		when(mockIncomeRepository.findById(1)).thenReturn(Optional.of(mockIncome));
		String status = incomeServiceImpl.deleteByIncomeId(incomeId);

		when(mockIncomeRepository.findByUserId("testuser")).thenReturn(new ArrayList<Income>());
		List<Income> result = incomeServiceImpl.findByUserId("testuser");
		assertTrue(result.isEmpty());
		verify(mockIncomeRepository, times(1)).deleteById(1);
		verify(mockIncomeRepository, times(1)).findById(1);
		assertEquals("Income removed for Income Id: " + incomeId, status);
	}
}
