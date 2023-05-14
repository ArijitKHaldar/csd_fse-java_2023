package com.arijit.idp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.arijit.idp.usecases.BudgetForecasting;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = { "http://localhost:3000", "http://127.0.0.1:3000" })
public class BudgetForecastingController {

	@Autowired
	private BudgetForecasting budgetForecasting;

	@ApiOperation(value = "Predict Savings For Next Month")
	@GetMapping("/predict/user/{userId}")
	public ResponseEntity<Double> predictSavings(@PathVariable String userId) throws NullValueEnteredException,
			NotAStringException, InvalidDataFormatException, ExpenditureNotFoundException, IncomeNotFoundException {

		log.info("Received request to predict savings for user: {}", userId);

		double predictedMonthlySavings = budgetForecasting.predictSavings(userId);
		return ResponseEntity.ok(predictedMonthlySavings);
	}
}
