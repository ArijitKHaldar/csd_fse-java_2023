package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String query = "select expenditure_id, expenditure_date, expenditure_amount, expenditure_tag from expenditure where user_id = ?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditure_id(rs.getInt("expenditure_id"));
				usr.setUser_id(user_id);
				usr.setExpenditure_date(rs.getDate("expenditure_date"));
				usr.setExpenditure_amount(rs.getDouble("expenditure_amount"));
				usr.setExpenditure_tag(rs.getString("expenditure_tag"));
				expenditureList.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return expenditureList;
	}

	@Override
	public List<Expenditure> getByUserIdAndMonth(String user_id, int month) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount, expenditure_tag from expenditure where user_id = ? and MONTH(expenditure_date)=?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_id);
			ps.setInt(2, month);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditure_id(rs.getInt("expenditure_id"));
				usr.setUser_id(user_id);
				usr.setExpenditure_date(rs.getDate("expenditure_date"));
				usr.setExpenditure_amount(rs.getDouble("expenditure_amount"));
				usr.setExpenditure_tag(rs.getString("expenditure_tag"));
				expenditureList.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return expenditureList;
	}

	@Override
	public List<Expenditure> getByUserIdAndYear(String user_id, int year) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount, expenditure_tag from expenditure where user_id = ? and YEAR(expenditure_date)=?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_id);
			ps.setInt(2, year);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditure_id(rs.getInt("expenditure_id"));
				usr.setUser_id(user_id);
				usr.setExpenditure_date(rs.getDate("expenditure_date"));
				usr.setExpenditure_amount(rs.getDouble("expenditure_amount"));
				usr.setExpenditure_tag(rs.getString("expenditure_tag"));
				expenditureList.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return expenditureList;
	}

	@Override
	public List<Expenditure> getByUserIdAndTag(String user_id, String expenditure_tag) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount, expenditure_tag from expenditure where user_id = ? and expenditure_tag = ?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_id);
			ps.setString(2, expenditure_tag);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditure_id(rs.getInt("expenditure_id"));
				usr.setUser_id(user_id);
				usr.setExpenditure_date(rs.getDate("expenditure_date"));
				usr.setExpenditure_amount(rs.getDouble("expenditure_amount"));
				usr.setExpenditure_tag(rs.getString("expenditure_tag"));
				expenditureList.add(usr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return expenditureList;
	}

	@Override
	public void updateByExpenditureId(Expenditure expenditure, int expenditure_id) {
		String query = "update expenditure set user_id=?, expenditure_date=?, expenditure_amount=?, expenditure_tag=? where expenditure_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, expenditure.getUser_id());
			ps.setDate(2, expenditure.getExpenditure_date());
			ps.setDouble(3, expenditure.getExpenditure_amount());
			ps.setString(4, expenditure.getExpenditure_tag());
			ps.setInt(5, expenditure_id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure updated for expenditure id " + expenditure_id);
			} else
				System.out.println("Invalid expenditure id!!");
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
	public void deleteByExpenditureId(int expenditure_id) {
		// TODO Auto-generated method stub
		
	}

}
