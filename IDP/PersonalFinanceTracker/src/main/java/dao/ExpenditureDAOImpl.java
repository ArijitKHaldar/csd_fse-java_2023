package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
		String query = "insert into expenditure (user_id, expenditure_date, expenditure_amount, expenditure_tag) values (?,?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			System.out.println(con);
			ps = con.prepareStatement(query);
			ps.setString(1, expenditure.getUser_id());
			ps.setDate(2, expenditure.getExpenditure_date());
			ps.setDouble(3, expenditure.getExpenditure_amount());
			ps.setString(4, expenditure.getExpenditure_tag());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure saved for user_id=" + expenditure.getUser_id());
			} else
				System.out.println("invalid user_id=" + expenditure.getUser_id());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
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
