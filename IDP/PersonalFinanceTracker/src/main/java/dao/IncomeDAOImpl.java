package dao;

import java.util.List;

import javax.sql.DataSource;

import model.Income;

public class IncomeDAOImpl implements IncomeDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Income income) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Income getByUserId(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Income income) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByIncomeId(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Income> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
