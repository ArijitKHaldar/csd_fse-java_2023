package com.arijit.idp.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.exception.IncomeNotFoundException;
import com.arijit.idp.exception.InvalidDataFormatException;
import com.arijit.idp.exception.NotAStringException;
import com.arijit.idp.exception.NullValueEnteredException;
import com.arijit.idp.usecases.BudgetForecasting;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class BudgetForecastingController {

	@Autowired
	private BudgetForecasting budgetForecasting;

	@ApiOperation(value = "Predict Savings For Next Month")
	@GetMapping("/predict/user/{userId}/date/{currentDate}")
	public ResponseEntity<Double> predictSavings(@PathVariable String userId, @PathVariable Date currentDate)
			throws Exception {
		double predictedMonthlySavings = budgetForecasting.predictSavings(userId, currentDate);
		return ResponseEntity.ok(predictedMonthlySavings);
	}

	@ExceptionHandler({ ExpenditureNotFoundException.class, IncomeNotFoundException.class,
			InvalidDataFormatException.class, NotAStringException.class, NullValueEnteredException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleBadRequestException(Exception ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleGenericException(Exception ex) {
		return "An error occurred !";
	}
}
