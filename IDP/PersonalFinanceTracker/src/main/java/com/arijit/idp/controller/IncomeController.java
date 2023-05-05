package com.arijit.idp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Income;
import com.arijit.idp.exception.IncomeAlreadyPresentException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.service.IncomeService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	private IncomeService service;

	@ApiOperation(value = "Insert new Income credit for a user")
	@PostMapping("/v1")
	public ResponseEntity<Income> insertIncome(@RequestBody Income income)
			throws IncomeAlreadyPresentException, InvalidDataFormatException {

		log.info("Received insert income request for userId: {}", income.getUserId());
		return new ResponseEntity<>(service.insertIncome(income), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find list of all incomes for a particular user")
	@GetMapping("/v1/userid/{userId}")
	public ResponseEntity<List<Income>> findByUserId(@PathVariable String userId)
			throws IncomeNotFoundException, NullValueEnteredException, NotAStringException {

		log.info("Received find income request for userId: {}", userId);
		return new ResponseEntity<>(service.findByUserId(userId), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Find list of all incomes for a particular user in a particular month")
	@GetMapping("/v1/userid/{userId}/month/{month}")
	public ResponseEntity<List<Income>> findByUserIdAndMonth(@PathVariable String userId, @PathVariable int month)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Received find income request for userId: {} and month: {}", userId, month);
		return new ResponseEntity<>(service.findByUserIdAndMonth(userId, month), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Find list of all incomes for a particular user in a particular year")
	@GetMapping("/v1/userid/{userId}/year/{year}")
	public ResponseEntity<List<Income>> findByUserIdAndYear(@PathVariable String userId, @PathVariable int year)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Received find income request for userId: {} and year: {}", userId, year);
		return new ResponseEntity<>(service.findByUserIdAndYear(userId, year), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Update entered income for a particular income id")
	@PutMapping("/v1/update")
	public ResponseEntity<Income> updateByIncomeId(@RequestParam int incomeId, @RequestBody Income updatedIncome)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Received update request by income id: {}", incomeId);
		return new ResponseEntity<>(service.updateByIncomeId(incomeId, updatedIncome), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete entered income for a particular income id")
	@DeleteMapping("/v1/delete/id/{incomeId}")
	public ResponseEntity<Object> deleteByIncomeId(@PathVariable int incomeId)
			throws NullValueEnteredException, InvalidDataFormatException, IncomeNotFoundException {

		log.info("Received delete request by income id: {}", incomeId);
		service.deleteByIncomeId(incomeId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
