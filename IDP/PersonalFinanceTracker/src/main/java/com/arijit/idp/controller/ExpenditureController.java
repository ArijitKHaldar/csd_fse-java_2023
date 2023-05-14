package com.arijit.idp.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.ExpenditureService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/expenditure")
@CrossOrigin(origins = { "http://localhost:3000", "http://127.0.0.1:3000" })
public class ExpenditureController {

	@Autowired
	private ExpenditureService service;

	@ApiOperation(value = "Insert new Expenditure debit from a user")
	@PostMapping("/v1")
	public ResponseEntity<Expenditure> create(@RequestBody Expenditure expenditure)
			throws ExpenditureAlreadyPresentException, InvalidDataFormatException {

		log.info("Creating new expenditure for user: {}", expenditure.getUserId());

		Expenditure exp = this.service.create(expenditure);
		return new ResponseEntity<>(exp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user")
	@GetMapping("/v1/userid/{userId}")
	public ResponseEntity<List<Expenditure>> findByUserId(@PathVariable String userId)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException {

		log.info("Finding all expenditures for user: {}", userId);

		return new ResponseEntity<>(this.service.findByUserId(userId), HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular date")
	@GetMapping("/v1/userid/{userId}/date/{expenditureDate}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndExpenditureDate(@PathVariable String userId,
			@PathVariable Date expenditureDate) throws ExpenditureNotFoundException, NullValueEnteredException,
			NotAStringException, InvalidDataFormatException {

		log.info("Finding all expenditures for user: {} on date: {}", userId, expenditureDate);

		return new ResponseEntity<>(this.service.findByUserIdAndExpenditureDate(userId, expenditureDate),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular month")
	@GetMapping("/v1/userid/{userId}/month/{month}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndMonth(@PathVariable String userId, @PathVariable int month)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException,
			InvalidDataFormatException {

		log.info("Finding all expenditures for user: {} in month: {}", userId, month);

		return new ResponseEntity<>(this.service.findByUserIdAndMonth(userId, month), HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular year")
	@GetMapping("/v1/userid/{userId}/year/{year}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndYear(@PathVariable String userId, @PathVariable int year)
			throws ExpenditureNotFoundException, NullValueEnteredException, NotAStringException,
			InvalidDataFormatException {

		log.info("Finding all expenditures for user: {} in year: {}", userId, year);

		return new ResponseEntity<>(this.service.findByUserIdAndYear(userId, year), HttpStatus.OK);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user for a category")
	@GetMapping("/v1/userid/{userId}/category/{expenditureTag}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndExpenditureType(@PathVariable String userId,
			@PathVariable String expenditureTag) throws ExpenditureNotFoundException, CategoryNotFoundException {

		log.info("Finding all expenditures for user: {} with category: {}", userId, expenditureTag);

		return new ResponseEntity<>(this.service.findByUserIdAndExpenditureType(userId, expenditureTag), HttpStatus.OK);
	}

	@ApiOperation(value = "Update Expenditure data by expenditure id")
	@PutMapping("/v1/update")
	public ResponseEntity<Expenditure> update(@RequestParam int expenditureId, @RequestBody Expenditure expenditure)
			throws ExpenditureNotFoundException, NullValueEnteredException, InvalidDataFormatException {

		log.info("Update expenditure for expenditure id: {}", expenditureId);

		return new ResponseEntity<>(this.service.update(expenditureId, expenditure), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete entered expenditure for a particular expenditure id")
	@DeleteMapping("/v1/delete/id/{expenditureId}")
	public ResponseEntity<Object> delete(@PathVariable int expenditureId)
			throws ExpenditureNotFoundException, NullValueEnteredException, InvalidDataFormatException {

		log.info("Delete expenditure for expenditure id: {}", expenditureId);

		service.delete(expenditureId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
