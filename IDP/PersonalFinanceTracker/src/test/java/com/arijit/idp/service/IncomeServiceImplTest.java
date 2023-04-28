package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
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
import com.arijit.idp.exception.IncomeAlreadyPresentException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.repository.IncomeRepository;

@ExtendWith(MockitoExtension.class)
public class IncomeServiceImplTest {

	@InjectMocks
	private IncomeServiceImpl mockedService;

	@Mock
	private IncomeRepository mockRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testInsertIncome_IncomeInserted() throws IncomeAlreadyPresentException, IncomeNotFoundException,
			NullValueEnteredException, NotAStringException {

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
		mockedService.insertIncome(mockIncome);
		verify(mockRepository).save(mockIncome);

		List<Income> incomeList = new ArrayList<>();
		incomeList.add(mockIncome);
		when(mockRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncome = mockedService.findByUserId("testuser");
		assertEquals(mockIncome, testIncome.get(0));
	}
	
	@Test
	public void testInsertIncome_IncomeNotInserted() {
		
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
		when(mockRepository.existsById(1)).thenReturn(true);
		assertThrows(IncomeAlreadyPresentException.class, () -> mockedService.insertIncome(mockIncome));
		verify(mockRepository, never()).save(any(Income.class));
	}

	@Test
	public void testFindByUserId_UserIdFound() throws IncomeNotFoundException, NullValueEnteredException, NotAStringException {

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
		when(mockRepository.findByUserId(userId)).thenReturn(incomeList);
		List<Income> testIncomeTrue = mockedService.findByUserId(userId);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserId("newuserid"));
	}
	
	@Test
	public void testFindByUserId_UserIdNotFound() {
		when(mockRepository.findByUserId("testuser")).thenReturn(Collections.emptyList());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserId("testuser"));
	}

	@Test
	public void testFindByUserIdAndMonth_Found()
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {
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
		when(mockRepository.findByUserIdAndMonth(userId, month)).thenReturn(incomeList);
		List<Income> testIncomeTrue = mockedService.findByUserIdAndMonth(userId, month);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserIdAndMonth("newuserid", 6));
	}
	
	@Test
	public void testFindByUserIdAndMonth_NotFound() {
		when(mockRepository.findByUserIdAndMonth("testuser", 4)).thenReturn(Collections.emptyList());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserIdAndMonth("testuser", 4));
	}

	@Test
	public void testFindByUserIdAndYear_Found()
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {
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
		when(mockRepository.findByUserIdAndYear(userId, year)).thenReturn(incomeList);
		List<Income> testIncomeTrue = mockedService.findByUserIdAndYear(userId, year);
		assertEquals(mockIncome, testIncomeTrue.get(0));

		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserIdAndYear("newuserid", 2022));
	}
	
	@Test
	public void testFindByUserIdAndYear_NotFound() {
		when(mockRepository.findByUserIdAndYear("testuser", 2023)).thenReturn(Collections.emptyList());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserIdAndYear("testuser", 2023));
	}

	@Test
	public void testUpdateByIncomeId_Succeed()
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException, NotAStringException {
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
		when(mockRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncomeTrue = mockedService.findByUserId("testuser");
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

		when(mockRepository.findById(1)).thenReturn(Optional.of(mockIncomeUpdate));
		when(mockRepository.save(mockIncomeUpdate)).thenReturn(mockIncomeUpdate);
		Income updatedIncome = mockedService.updateByIncomeId(1, mockIncomeUpdate);
		verify(mockRepository, times(1)).save(mockIncomeUpdate);
		assertEquals(mockIncomeUpdate, updatedIncome);
		assertNotEquals(mockIncomeUpdate, mockIncome);
	}

	@Test
	public void testUpdateByIncomeId_Fail() {
		
		Income mockIncome = new Income("testuser", Date.valueOf("2023-01-01"), new BigDecimal(20000));
		
		when(mockRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.updateByIncomeId(1, mockIncome));
		verify(mockRepository, never()).save(any(Income.class));
	}
	
	@Test
	public void testDeleteByIncomeId_IncomeIdFound()
			throws IncomeNotFoundException, NullValueEnteredException, NotAStringException, InvalidDataFormatException {

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
		when(mockRepository.findByUserId("testuser")).thenReturn(incomeList);
		List<Income> testIncomeTrue = mockedService.findByUserId("testuser");
		assertEquals(1, testIncomeTrue.size());
		assertEquals(mockIncome, testIncomeTrue.get(0));

		when(mockRepository.findById(1)).thenReturn(Optional.of(mockIncome));
		mockedService.deleteByIncomeId(incomeId);

		when(mockRepository.findByUserId("testuser")).thenReturn(Collections.emptyList());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.findByUserId("testuser"));
		verify(mockRepository, times(1)).deleteById(1);
		verify(mockRepository, times(1)).findById(1);
	}
	
	@Test
	public void testDeleteByIncomeId_IncomeIdNotFound() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(IncomeNotFoundException.class, () -> mockedService.deleteByIncomeId(1));
		verify(mockRepository, never()).deleteById(1);
	}
}
