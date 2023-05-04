package com.arijit.idp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.arijit.idp.entity.Category;
import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.repository.ExpenditureRepository;

@ExtendWith(MockitoExtension.class)
public class ExpenditureServiceImplTest {

	@InjectMocks
	private ExpenditureServiceImpl mockedService;

	@Mock
	private ExpenditureRepository mockRepository;

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreateExpenditure_Success() throws ExpenditureAlreadyPresentException, InvalidDataFormatException {
		Expenditure expenditure = new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000"));
		when(mockRepository.existsById(expenditure.getExpenditureId())).thenReturn(false);
		when(mockRepository.save(expenditure)).thenReturn(expenditure);

		Expenditure savedExpenditure = mockedService.create(expenditure);

		assertNotNull(savedExpenditure);
		assertEquals(expenditure, savedExpenditure);
		verify(mockRepository).existsById(expenditure.getExpenditureId());
		verify(mockRepository).save(expenditure);
	}

	@Test
	public void testCreateExpenditure_AlreadyPresent() {
		Expenditure expenditure = new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000"));
		when(mockRepository.existsById(expenditure.getExpenditureId())).thenReturn(true);

		assertThrows(ExpenditureAlreadyPresentException.class, () -> mockedService.create(expenditure));

		verify(mockRepository).existsById(expenditure.getExpenditureId());
		verify(mockRepository, never()).save(any(Expenditure.class));
	}

	@Test
	public void testFindByUserId_Found()
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException {
		String userId = "testuser";
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000")));
		expenditures.add(new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000")));
		when(mockRepository.findByUserId(userId)).thenReturn(expenditures);

		List<Expenditure> result = mockedService.findByUserId(userId);

		assertEquals(expenditures, result);
	}

	@Test
	public void testFindByUserId_NotFound() {
		String userId = "testuser";
		when(mockRepository.findByUserId(userId)).thenReturn(Collections.emptyList());

		assertThrows(ExpenditureNotFoundException.class, () -> mockedService.findByUserId(userId));
	}

	@Test
	public void testFindByUserIdAndExpenditureDate_Found() throws ExpenditureNotFoundException,
			NullValueEnteredException, NotAStringException, InvalidDataFormatException {
		String userId = "user123";
		Date expenditureDate = Date.valueOf("2023-04-03");
		Expenditure expenditure1 = new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000"));
		Expenditure expenditure2 = new Expenditure("rent", 1, Date.valueOf("2023-04-03"), new BigDecimal("1000"));
		List<Expenditure> expenditures = new ArrayList<>();
		expenditures.add(expenditure1);
		expenditures.add(expenditure2);
		when(mockRepository.findByUserIdAndExpenditureDate(userId, expenditureDate)).thenReturn(expenditures);

		List<Expenditure> actualExpenditures = mockedService.findByUserIdAndExpenditureDate(userId, expenditureDate);

		assertEquals(expenditures, actualExpenditures);
		verify(mockRepository, times(1)).findByUserIdAndExpenditureDate(userId, expenditureDate);
	}

	@Test
	public void testFindByUserIdAndExpenditureDate_NotFound() {
		String userId = "user123";
		Date expenditureDate = Date.valueOf("2023-04-03");
		when(mockRepository.findByUserIdAndExpenditureDate(userId, expenditureDate))
				.thenReturn(Collections.emptyList());

		assertThrows(ExpenditureNotFoundException.class,
				() -> mockedService.findByUserIdAndExpenditureDate(userId, expenditureDate));
		verify(mockRepository, times(1)).findByUserIdAndExpenditureDate(userId, expenditureDate);
	}

	@Test
	public void testFindByUserIdAndMonth_Found() throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException {
		String userId = "user1";
		int month = 1;
		List<Expenditure> expectedExpenditures = new ArrayList<>();
		expectedExpenditures.add(new Expenditure());
		when(mockRepository.findByUserIdAndMonth(userId, month)).thenReturn(expectedExpenditures);

		List<Expenditure> actualExpenditures = mockedService.findByUserIdAndMonth(userId, month);

		assertEquals(expectedExpenditures, actualExpenditures);
		verify(mockRepository, times(1)).findByUserIdAndMonth(userId, month);
	}

	@Test
	public void testFindByUserIdAndMonth_NotFound() {
		String userId = "user1";
		int month = 1;
		when(mockRepository.findByUserIdAndMonth(userId, month)).thenReturn(new ArrayList<>());

		assertThrows(ExpenditureNotFoundException.class, () -> mockedService.findByUserIdAndMonth(userId, month));
		verify(mockRepository, times(1)).findByUserIdAndMonth(userId, month);
	}

	@Test
	public void testFindByUserIdAndYear_Found() throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException {
		String userId = "user1";
		int year = 2023;

		Expenditure expenditure1 = new Expenditure();
		expenditure1.setUserId(userId);
		expenditure1.setExpenditureDate(Date.valueOf("2023-03-15"));

		Expenditure expenditure2 = new Expenditure();
		expenditure2.setUserId(userId);
		expenditure2.setExpenditureDate(Date.valueOf("2023-04-22"));
		List<Expenditure> expectedExpenditures = new ArrayList<>();
		expectedExpenditures.add(expenditure1);
		expectedExpenditures.add(expenditure2);
		when(mockRepository.findByUserIdAndYear(anyString(), anyInt())).thenReturn(expectedExpenditures);

		List<Expenditure> actualExpenditures = mockedService.findByUserIdAndYear(userId, year);

		assertEquals(expectedExpenditures, actualExpenditures);
		verify(mockRepository, times(1)).findByUserIdAndYear(userId, year);
	}

	@Test
	public void testFindByUserIdAndYear_WithNullUserId() {
		String userId = null;
		int year = 2023;

		assertThrows(NullValueEnteredException.class, () -> mockedService.findByUserIdAndYear(userId, year));
		verify(mockRepository, never()).findByUserIdAndYear(anyString(), anyInt());
	}

	@Test
	public void testFindByUserIdAndYear_WithInvalidYear() {
		String userId = "123";
		int year = 999;

		assertThrows(InvalidDataFormatException.class, () -> mockedService.findByUserIdAndYear(userId, year));
		verify(mockRepository, never()).findByUserIdAndYear(anyString(), anyInt());
	}

	@Test
	public void testFindByUserIdAndYear_NotFound() {
		String userId = "123";
		int year = 2023;

		when(mockRepository.findByUserIdAndYear(anyString(), anyInt())).thenReturn(new ArrayList<>());

		assertThrows(ExpenditureNotFoundException.class, () -> mockedService.findByUserIdAndYear(userId, year));
		verify(mockRepository, times(1)).findByUserIdAndYear(userId, year);
	}

	@Test
	public void testFindByUserIdAndExpenditureType_ExpendituresFound()
			throws ExpenditureNotFoundException, CategoryNotFoundException {
		String userId = "user1";
		String expenditureTag = "tag1";
		List<Expenditure> expenditures = new ArrayList<>();
		Expenditure expenditure1 = new Expenditure();
		expenditure1.setUserId(userId);
		expenditure1.setExpenditureAmount(new BigDecimal(100.0));
		expenditure1.setExpenditureDate(Date.valueOf("2023-04-26"));
		expenditure1.setCategory(new Category(1, expenditureTag));
		expenditures.add(expenditure1);

		when(mockRepository.findByUserId(anyString())).thenReturn(expenditures);
		when(mockRepository.findByUserIdAndCategory(anyString(), anyString())).thenReturn(expenditures);

		List<Expenditure> result = mockedService.findByUserIdAndExpenditureType(userId, expenditureTag);

		verify(mockRepository).findByUserId(userId);
		verify(mockRepository).findByUserIdAndCategory(userId, expenditureTag);
		assertEquals(expenditures, result);
	}

	@Test
	public void testFindByUserIdAndExpenditureType_NoExpendituresFound() {
		String userId = "user1";
		String expenditureTag = "tag1";
		List<Expenditure> expenditures = new ArrayList<>();

		when(mockRepository.findByUserId(anyString())).thenReturn(expenditures);

		assertThrows(ExpenditureNotFoundException.class,
				() -> mockedService.findByUserIdAndExpenditureType(userId, expenditureTag));
	}

	@Test
	public void testFindByUserIdAndExpenditureType_NoCategoryFound() {
		String userId = "user1";
		String expenditureTag = "invalidCategory";
		List<Expenditure> emptyList = new ArrayList<>();
		List<Expenditure> expenditures = new ArrayList<>();
		Expenditure expenditure1 = new Expenditure();
		expenditure1.setUserId(userId);
		expenditure1.setExpenditureAmount(new BigDecimal(100.0));
		expenditure1.setExpenditureDate(Date.valueOf("2023-04-26"));
		expenditure1.setCategory(new Category(1, expenditureTag));
		expenditures.add(expenditure1);
		when(mockRepository.findByUserId(userId)).thenReturn(expenditures);
		when(mockRepository.findByUserIdAndCategory(userId, expenditureTag)).thenReturn(emptyList);

		assertThrows(CategoryNotFoundException.class,
				() -> mockedService.findByUserIdAndExpenditureType(userId, expenditureTag));

		verify(mockRepository, times(1)).findByUserId(userId);
		verify(mockRepository, times(1)).findByUserIdAndCategory(userId, expenditureTag);
	}

	@Test
	public void testUpdateExpenditure_Success()
			throws NullValueEnteredException, InvalidDataFormatException, ExpenditureNotFoundException {

		Expenditure existingExpenditure = new Expenditure();
		existingExpenditure.setUserId("user123");
		existingExpenditure.setCategoryId(1);
		existingExpenditure.setExpenditureAmount(new BigDecimal(50.0));
		existingExpenditure.setExpenditureDate(Date.valueOf("2023-04-26"));
		Field field = null;
		try {
			field = existingExpenditure.getClass().getDeclaredField("expenditureId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(existingExpenditure, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		Expenditure updatedExpenditure = new Expenditure();
		updatedExpenditure.setUserId("user123");
		updatedExpenditure.setCategoryId(1);
		updatedExpenditure.setExpenditureAmount(new BigDecimal(75.0));
		updatedExpenditure.setExpenditureDate(Date.valueOf("2023-04-26"));

		Mockito.when(mockRepository.findById(existingExpenditure.getExpenditureId()))
				.thenReturn(Optional.of(existingExpenditure));
		Mockito.when(mockRepository.save(existingExpenditure)).thenReturn(updatedExpenditure);

		Expenditure result = mockedService.update(1, updatedExpenditure);

		assertNotNull(result);
		assertEquals(existingExpenditure, result);
		Mockito.verify(mockRepository, Mockito.times(1)).save(existingExpenditure);
	}

	@Test
	public void testUpdateExpenditure_NotFound() {
		int expenditureId = 1;
		Expenditure expenditure = new Expenditure();
		expenditure.setExpenditureDate(Date.valueOf("2023-04-26"));
		expenditure.setExpenditureAmount(new BigDecimal(100.0));
		expenditure.setCategoryId(1);
		when(mockRepository.findById(expenditureId)).thenReturn(Optional.empty());

		assertThrows(ExpenditureNotFoundException.class, () -> {
			mockedService.update(expenditureId, expenditure);
		});

		verify(mockRepository, times(1)).findById(expenditureId);
		verifyNoMoreInteractions(mockRepository);
	}

	@Test
	public void testDelete_ValidExpenditureId()
			throws NullValueEnteredException, InvalidDataFormatException, ExpenditureNotFoundException {
		Expenditure expenditure = new Expenditure();
		expenditure.setUserId("user123");
		expenditure.setCategoryId(1);
		expenditure.setExpenditureAmount(new BigDecimal(50.0));
		expenditure.setExpenditureDate(Date.valueOf("2023-04-26"));
		Field field = null;
		try {
			field = expenditure.getClass().getDeclaredField("expenditureId");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			field.set(expenditure, 1);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		when(mockRepository.findById(1)).thenReturn(Optional.of(expenditure));
		mockedService.delete(1);

		when(mockRepository.findByUserId("user123")).thenReturn(Collections.emptyList());

		assertThrows(ExpenditureNotFoundException.class, () -> mockedService.findByUserId("user123"));
		verify(mockRepository, times(1)).delete(expenditure);
		verify(mockRepository, times(1)).findByUserId("user123");
	}

	@Test
	public void testDelete_ExpenditureNotFound() {
		when(mockRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(ExpenditureNotFoundException.class, () -> mockedService.delete(1));
	}

	@Test
	public void testDelete_Exceptions() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.delete(0));
	}

	@Test
	public void testCreate_Exceptions() {
		Expenditure expenditure = new Expenditure();
		expenditure.setExpenditureAmount(new BigDecimal(-1));
		assertThrows(InvalidDataFormatException.class, () -> mockedService.create(expenditure));
	}

	@Test
	public void testFindByUserId_Exceptions() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.findByUserId(null));
	}

	@Test
	public void testFindByUserIdAndExpenditureDate_Exceptions() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.findByUserIdAndExpenditureDate(null, null));
	}

	@Test
	public void testFindByUserIdAndMonth_Exceptions() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.findByUserIdAndMonth(null, 5));
		assertThrows(InvalidDataFormatException.class, () -> mockedService.findByUserIdAndMonth("testcode", 0));
	}

	@Test
	public void testFindByUserIdAndYear_Exception() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.findByUserIdAndYear(null, 2023));
		assertThrows(InvalidDataFormatException.class, () -> mockedService.findByUserIdAndYear("testcode", 0));
	}

	@Test
	public void testUpdate_Exception() {
		assertThrows(NullValueEnteredException.class, () -> mockedService.update(0, null));
	}
}
