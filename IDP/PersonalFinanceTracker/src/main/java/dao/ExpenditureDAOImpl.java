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
	public List<Expenditure> getByUserId(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expenditure> getByUserIdAndMonth(String user_id, int month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expenditure> getByUserIdAndYear(String user_id, int year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Expenditure> getByUserIdAndTag(String user_id, String expenditure_tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateByExpenditureId(Expenditure expenditure, int expenditure_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByExpenditureId(int expenditure_id) {
		// TODO Auto-generated method stub
		
	}

}
