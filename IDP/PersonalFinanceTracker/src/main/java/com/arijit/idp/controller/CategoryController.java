package com.arijit.idp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arijit.idp.entity.Category;
import com.arijit.idp.service.CategoryService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@ApiOperation(value = "Insert new Category of Expenditure")
	@PostMapping("/v1")
	public Category create(@RequestBody Category category) {
		return service.create(category);
	}
	
	@ApiOperation(value = "View All Available Expenditure Categories")
	@GetMapping("/v1/view")
	public List<Category> findAllExpenditureTags() {
		List<Category> categories = service.findAllExpenditureTags();
		return categories;
	}
	
	@ApiOperation(value = "Delete a particular expenditure category")
	@DeleteMapping("/v1/delete/{expenditureTag}")
	public void deleteByExpenditureTag(@PathVariable String expenditureTag) {
		service.deleteByExpenditureTag(expenditureTag);
	}
}
