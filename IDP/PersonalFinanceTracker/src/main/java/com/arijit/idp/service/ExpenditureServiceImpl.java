package com.arijit.idp.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.repository.ExpenditureRepository;

@Service
public class ExpenditureServiceImpl implements ExpenditureService {

	@Autowired
	private ExpenditureRepository expenditureRepository;

	// Create
	@Override
	public Expenditure create(Expenditure expenditure)
			throws ExpenditureAlreadyPresentException, InvalidDataFormatException {
		if (expenditureRepository.existsById(expenditure.getExpenditureId())) {
			throw new ExpenditureAlreadyPresentException();
		}
		if (expenditure.getExpenditureAmount().doubleValue() < 0) {
			throw new InvalidDataFormatException("Expenditure cannot be negative!");
		}
		Expenditure savedExpenditure = expenditureRepository.save(expenditure);
		return savedExpenditure;
	}

	// Retrieve
	@Override
	public List<Expenditure> findByUserId(String userId)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException {
		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		if (!(userId instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}

		List<Expenditure> expendituresByUserId = expenditureRepository.findByUserId(userId);
		if (expendituresByUserId.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserId;
	}

	@Override
	public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException,
			InvalidDataFormatException {

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		if (!(userId instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = dateFormat.format(expenditureDate);
		if (!Pattern.matches("\\d{4}-\\d{2}-\\d{2}", dateString)) {
			throw new InvalidDataFormatException("Date should be in YYYY-MM-DD format");
		}

		List<Expenditure> expendituresByUserIdAndDate = expenditureRepository.findByUserIdAndExpenditureDate(userId,
				expenditureDate);
		if (expendituresByUserIdAndDate.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndDate;
	}

	@Override
	public List<Expenditure> findByUserIdAndMonth(String userId, int month) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException {

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		if (!(userId instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}
		if ((!(Integer.valueOf(month) instanceof Integer)) || month < 1 || month > 12) {
			throw new InvalidDataFormatException("Month should be between 1 to 12");
		}
		List<Expenditure> expendituresByUserIdAndMonth = expenditureRepository.findByUserIdAndMonth(userId, month);
		if (expendituresByUserIdAndMonth.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndMonth;
	}

	@Override
	public List<Expenditure> findByUserIdAndYear(String userId, int year) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException {

		if (userId == null || userId.isEmpty()) {
			throw new NullValueEnteredException("User Id cannot be empty");
		}
		if (!(userId instanceof String)) {
			throw new NotAStringException("The entered password is not a String value");
		}
		if ((!(Integer.valueOf(year) instanceof Integer)) || year < 1000 || year > 9999) {
			throw new InvalidDataFormatException("Invalid year entered");
		}

		List<Expenditure> expendituresByUserIdAndYear = expenditureRepository.findByUserIdAndYear(userId, year);
		if (expendituresByUserIdAndYear.isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		return expendituresByUserIdAndYear;
	}

	@Override
	public List<Expenditure> findByUserIdAndExpenditureType(String userId, String expenditureTag)
			throws ExpenditureNotFoundException, CategoryNotFoundException {
		if (expenditureRepository.findByUserId(userId).isEmpty()) {
			throw new ExpenditureNotFoundException();
		}
		List<Expenditure> expenditure = expenditureRepository.findByUserIdAndCategory(userId, expenditureTag);
		if (expenditure.isEmpty()) {
			throw new CategoryNotFoundException();
		}
		return expenditure;
	}

	@Override
	public Expenditure update(int expenditureId, Expenditure expenditure)
			throws NullValueEnteredException, InvalidDataFormatException, ExpenditureNotFoundException {

		if (expenditureId == 0) {
			throw new NullValueEnteredException("Expenditure Id cannot be empty");
		}
		if (!(Integer.valueOf(expenditureId) instanceof Integer)) {
			throw new InvalidDataFormatException();
		}

		Optional<Expenditure> existingExpenditure = expenditureRepository.findById(expenditureId);
		if (!existingExpenditure.isPresent()) {
			throw new ExpenditureNotFoundException("Expenditure not found with id: " + expenditureId);
		}
		Expenditure updatedExpenditure = existingExpenditure.get();
		updatedExpenditure.setExpenditureDate(expenditure.getExpenditureDate());
		updatedExpenditure.setExpenditureAmount(expenditure.getExpenditureAmount());
		updatedExpenditure.setCategoryId(expenditure.getCategoryId());
		expenditureRepository.save(updatedExpenditure);

		return updatedExpenditure;
	}

	@Override
	public void delete(int expenditureId)
			throws NullValueEnteredException, InvalidDataFormatException, ExpenditureNotFoundException {

		if (expenditureId == 0) {
			throw new NullValueEnteredException("Expenditure Id cannot be empty");
		}
		if (!(Integer.valueOf(expenditureId) instanceof Integer)) {
			throw new InvalidDataFormatException();
		}
		Expenditure expenditure = expenditureRepository.findById(expenditureId).orElseThrow(
				() -> new ExpenditureNotFoundException("Expenditure not found with expenditure id " + expenditureId));
		expenditureRepository.delete(expenditure);
	}

}
