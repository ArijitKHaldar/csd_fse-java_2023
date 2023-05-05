package com.arijit.idp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.usecases.SavingsCalculation;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/savings/calculation/v1")
public class SavingsCalculationController {

	@Autowired
	private SavingsCalculation savingsCalculation;

	@ApiOperation(value = "Calculate Monthly Percentage of Savings")
	@GetMapping("/user/{userId}/savings/month/{month}")
	public ResponseEntity<Double> calculateMonthlySavingsPercentage(@PathVariable String userId,
			@PathVariable int month) throws NullValueEnteredException, NotAStringException, InvalidDataFormatException,
			ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Received request to calculate monthly savings percentage for user: {}", userId);

		double savingsPercentage = savingsCalculation.calculateMonthlySavingsPercentage(userId, month);
		return new ResponseEntity<>(savingsPercentage, HttpStatus.OK);
	}

	@ApiOperation(value = "Calculate Yearly Percentage of Savings")
	@GetMapping("/user/{userId}/savings/year/{year}")
	public ResponseEntity<?> calculateYearlySavingsPercentage(@PathVariable String userId, @PathVariable int year)
			throws NullValueEnteredException, NotAStringException, InvalidDataFormatException,
			ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Received request to calculate yearly savings percentage for user: {}", userId);

		double savingsPercentage = savingsCalculation.calculateYearlySavingsPercentage(userId, year);
		return new ResponseEntity<>(savingsPercentage, HttpStatus.OK);
	}
}