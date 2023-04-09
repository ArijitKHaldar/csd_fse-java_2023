package com.arijit.dao;

import javax.sql.DataSource;

import com.arijit.model.Category;

public class CategoryDAOImpl implements CategoryDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub

	}

	@Override
	public Category getByExpenditureId(int expenditure_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByCategoryId(Category category_id) {
		// TODO Auto-generated method stub

	}

}
