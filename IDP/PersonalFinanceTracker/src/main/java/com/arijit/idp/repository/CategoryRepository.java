package com.arijit.idp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arijit.idp.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	public Category findByExpenditureId(int expenditureId);

}
