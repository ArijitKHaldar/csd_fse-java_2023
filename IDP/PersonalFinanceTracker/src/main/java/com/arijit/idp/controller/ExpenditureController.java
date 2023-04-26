package com.arijit.idp.controller;

import java.sql.Date;
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

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.exception.ExpenditureAlreadyPresentException;
import com.arijit.idp.exception.ExpenditureNotFoundException;
import com.arijit.idp.service.ExpenditureService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/expenditure")
public class ExpenditureController {

	@Autowired
	private ExpenditureService service;

	@ApiOperation(value = "Insert new Expenditure debit from a user")
	@PostMapping("/v1")
	public ResponseEntity<Expenditure> create(@RequestBody Expenditure expenditure) throws ExpenditureAlreadyPresentException {
		Expenditure exp = this.service.create(expenditure);
		return new ResponseEntity<>(exp, HttpStatus.CREATED);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user")
	@GetMapping("/v1/userid/{userId}")
	public ResponseEntity<List<Expenditure>> findByUserId(@PathVariable String userId) throws ExpenditureNotFoundException {
		return new ResponseEntity<>(this.service.findByUserId(userId), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular date")
	@GetMapping("/v1/userid/{userId}/date/{expenditureDate}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndExpenditureDate(@PathVariable String userId,
			@PathVariable Date expenditureDate) throws ExpenditureNotFoundException {
		return new ResponseEntity<>(this.service.findByUserIdAndExpenditureDate(userId, expenditureDate), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular month")
	@GetMapping("/v1/userid/{userId}/month/{month}")
	public ResponseEntity<List<Expenditure>> findByUserIdAndMonth(@PathVariable String userId, @PathVariable int month)
			throws ExpenditureNotFoundException {
		return new ResponseEntity<>(this.service.findByUserIdAndMonth(userId, month), HttpStatus.FOUND);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user on a particular year")
	@GetMapping("/v1/userid/{userId}/year/{year}")
	public List<Expenditure> findByUserIdAndYear(@PathVariable String userId, @PathVariable int year)
			throws ExpenditureNotFoundException {
		return service.findByUserIdAndYear(userId, year);
	}

	@ApiOperation(value = "Find list of all expenditures for a particular user for a category")
	@GetMapping("/v1/userid/{userId}/category/{expenditureTag}")
	public List<Expenditure> findByUserIdAndExpenditureType(@PathVariable String userId,
			@PathVariable String expenditureTag) throws ExpenditureNotFoundException, CategoryNotFoundException {
		return service.findByUserIdAndExpenditureType(userId, expenditureTag);
	}

	@ApiOperation(value = "Update Expenditure data by expenditure id")
	@PutMapping("/v1/update")
	public Expenditure update(@RequestParam int expenditureId, @RequestBody Expenditure expenditure)
			throws ExpenditureNotFoundException {
		return service.update(expenditureId, expenditure);
	}

	@ApiOperation(value = "Delete entered expenditure for a particular expenditure id")
	@DeleteMapping("/v1/delete/id/{expenditureId}")
	public void delete(@PathVariable int expenditureId) throws ExpenditureNotFoundException {
		service.delete(expenditureId);
	}
}
