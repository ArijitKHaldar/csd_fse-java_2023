package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arijit.idp.repository.CategoryRepository;

@Service
public class CategoryServiceImpl {
	
	@Autowired
	private CategoryRepository categoryRepository;

}
