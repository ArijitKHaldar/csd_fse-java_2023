package com.arijit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.arijit.model.Expenditure;

public class ExpenditureDAOImpl implements ExpenditureDAO {
	
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Expenditure expenditure) {
		String query = "insert into expenditure (user_id, expenditure_date, expenditure_amount) values (?,?,?);";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			System.out.println(con);
			ps = con.prepareStatement(query);
			ps.setString(1, expenditure.getUserId());
			ps.setDate(2, expenditure.getExpenditureDate());
			ps.setDouble(3, expenditure.getExpenditureAmount());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure saved for user_id=" + expenditure.getUserId());
			} else
				System.out.println("invalid user_id=" + expenditure.getUserId());
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
	public int getLastExpenditureId() {
		String query = "select max(expenditure_id) from expenditure;";
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int expenditureId = 0;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			if(rs.next()) {
				expenditureId = rs.getInt("max(expenditure_id)");
			}
		}catch(SQLException e) {
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
		return expenditureId;
	}

	@Override
	public List<Expenditure> getByUserId(String userId) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount from expenditure where user_id = ?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditureId(rs.getInt("expenditure_id"));
				usr.setUserId(userId);
				usr.setExpenditureDate(rs.getDate("expenditure_date"));
				usr.setExpenditureAmount(rs.getDouble("expenditure_amount"));
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
	public List<Expenditure> getByUserIdAndMonth(String userId, int month) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount from expenditure where user_id = ? and MONTH(expenditure_date)=?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userId);
			ps.setInt(2, month);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditureId(rs.getInt("expenditure_id"));
				usr.setUserId(userId);
				usr.setExpenditureDate(rs.getDate("expenditure_date"));
				usr.setExpenditureAmount(rs.getDouble("expenditure_amount"));
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
	public List<Expenditure> getByUserIdAndYear(String userId, int year) {
		String query = "select expenditure_id, expenditure_date, expenditure_amount from expenditure where user_id = ? and YEAR(expenditure_date)=?";
		List<Expenditure> expenditureList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userId);
			ps.setInt(2, year);
			rs = ps.executeQuery();
			while (rs.next()) {
				Expenditure usr = new Expenditure();
				usr.setExpenditureId(rs.getInt("expenditure_id"));
				usr.setUserId(userId);
				usr.setExpenditureDate(rs.getDate("expenditure_date"));
				usr.setExpenditureAmount(rs.getDouble("expenditure_amount"));
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
	public void updateByExpenditureId(Expenditure expenditure, int expenditureId) {
		String query = "update expenditure set user_id=?, expenditure_date=?, expenditure_amount=? where expenditure_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, expenditure.getUserId());
			ps.setDate(2, expenditure.getExpenditureDate());
			ps.setDouble(3, expenditure.getExpenditureAmount());
			ps.setInt(4, expenditureId);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure updated for expenditure id " + expenditureId);
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
	public void deleteByExpenditureId(int expenditureId) {
		String query = "delete from expenditure where expenditure_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expenditureId);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure deleted with expenditure id: " + expenditureId);
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
}
