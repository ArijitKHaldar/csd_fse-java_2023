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

@RestController
@RequestMapping("/api/v1")
public class SavingsCalculationController {

	@Autowired
	private SavingsCalculation savingsCalculation;

	@ApiOperation(value = "Calculate Monthly Percentage of Savings")
	@GetMapping("/calculation/user/{userId}/savings/month/{month}")
	public ResponseEntity<?> calculateMonthlySavingsPercentage(@PathVariable String userId, @PathVariable int month) {
		try {
			double savingsPercentage = savingsCalculation.calculateMonthlySavingsPercentage(userId, month);
			return ResponseEntity.ok(savingsPercentage);
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (ExpenditureNotFoundException | IncomeNotFoundException e) {
			if (e instanceof ExpenditureNotFoundException) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
	}

	@ApiOperation(value = "Calculate Yearly Percentage of Savings")
	@GetMapping("/user/{userId}/savings/year/{year}")
	public ResponseEntity<?> calculateYearlySavingsPercentage(@PathVariable String userId, @PathVariable int year) {
		try {
			double savingsPercentage = savingsCalculation.calculateYearlySavingsPercentage(userId, year);
			return ResponseEntity.ok(savingsPercentage);
		} catch (NullValueEnteredException | NotAStringException | InvalidDataFormatException e) {
			if (e instanceof InvalidDataFormatException) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (ExpenditureNotFoundException | IncomeNotFoundException e) {
			if (e instanceof ExpenditureNotFoundException) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
	}
}