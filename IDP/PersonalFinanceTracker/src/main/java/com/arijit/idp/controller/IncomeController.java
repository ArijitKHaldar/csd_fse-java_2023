package com.arijit.idp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.arijit.idp.service.IncomeService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
	
	@Autowired
	private IncomeService service;
	
	@ApiOperation(value = "Insert new Income credit for a user")
	@PostMapping("/v1")
	public Income insertIncome(@RequestBody Income income) {
		return service.insertIncome(income);
	}
	
	@ApiOperation(value = "Find list of all incomes for a particular user")
	@GetMapping("/v1/userid/{userId}")
	public List<Income> findByUserId(@PathVariable String userId) {
		return service.findByUserId(userId);
	}
	
	@ApiOperation(value = "Find list of all incomes for a particular user in a particular month")
	@GetMapping("/v1/userid/{userId}/month/{month}")
	public List<Income> findByUserIdAndMonth(@PathVariable String userId, @PathVariable int month) {
		return service.findByUserIdAndMonth(userId, month);
	}
	
	@ApiOperation(value = "Find list of all incomes for a particular user in a particular year")
	@GetMapping("/v1/userid/{userId}/year/{year}")
	public List<Income> findByUserIdAndYear(@PathVariable String userId, @PathVariable int year) {
		return service.findByUserIdAndYear(userId, year);
	}
	
	@ApiOperation(value = "Update entered income for a particular income id")
	@PutMapping("/v1/update")
	public Income updateByIncomeId(@RequestParam int incomeId, @RequestBody  Income updatedIncome) {
		return service.updateByIncomeId(incomeId, updatedIncome);
	}
	
	@ApiOperation(value = "Delete entered income for a particular income id")
	@DeleteMapping("/v1/delete/id/{incomeId}")
	public String deleteByIncomeId(@PathVariable int incomeId) {
		return service.deleteByIncomeId(incomeId);
	}
}
