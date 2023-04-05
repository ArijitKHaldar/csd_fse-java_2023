package dao;

import java.util.List;

import javax.sql.DataSource;

import model.Expenditure;

public class ExpenditureDAOImpl implements ExpenditureDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Expenditure expenditure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Expenditure getByUserId(int user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Expenditure expenditure) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByExpenditureId(int expenditure_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Expenditure> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
