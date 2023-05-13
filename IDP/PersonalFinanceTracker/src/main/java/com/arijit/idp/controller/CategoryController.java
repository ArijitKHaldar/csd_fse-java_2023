package com.arijit.idp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Category;
import com.arijit.idp.exception.CategoryAlreadyPresentException;
import com.arijit.idp.exception.CategoryNotFoundException;
import com.arijit.idp.service.CategoryService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@ApiOperation(value = "Insert new Category of Expenditure")
	@PostMapping("/v1")
	public ResponseEntity<Category> create(@RequestBody Category category) throws CategoryAlreadyPresentException {

		log.info("Creating new Category of Expenditure with Name: {}", category.getExpenditureTag());

		return new ResponseEntity<>(this.service.create(category), HttpStatus.CREATED);
	}

	@ApiOperation(value = "View All Available Expenditure Categories")
	@GetMapping("/v1/view")
	public ResponseEntity<List<Category>> findAllExpenditureTags() throws CategoryNotFoundException {

		log.info("Fetching all available Expenditure Categories");

		List<Category> categories = service.findAllExpenditureTags();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@ApiOperation(value = "Delete a particular expenditure category")
	@DeleteMapping("/v1/delete/{expenditureTag}")
	public ResponseEntity<Object> deleteByExpenditureTag(@PathVariable String expenditureTag)
			throws CategoryNotFoundException {

		log.info("Deleting Expenditure Category with Tag: {}", expenditureTag);

		service.deleteByExpenditureTag(expenditureTag);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
