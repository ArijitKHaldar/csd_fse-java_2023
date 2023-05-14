package com.arijit.idp.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.usecases.FinancialGoal;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = { "http://localhost:3000", "http://127.0.0.1:3000" })
public class FinancialGoalController {

	@Autowired
	private FinancialGoal financialGoal;

	@ApiOperation(value = "Calculate Percentage Completion of Savings Towards Set Target Amount")
	@GetMapping("/goal/user/{userId}/date/{date}/amount/{savingsGoalAmount}")
	public ResponseEntity<Double> calculateSavingsCompletion(@PathVariable String userId, @PathVariable Date date,
			@PathVariable double savingsGoalAmount) throws NullValueEnteredException, NotAStringException,
			InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Received request to calculate percentage completion of financial goal for user: {}", userId);

		double percentageCompletion = financialGoal.calculateSavingsCompletion(userId, date, savingsGoalAmount);

		return new ResponseEntity<>(percentageCompletion, HttpStatus.OK);
	}
}
