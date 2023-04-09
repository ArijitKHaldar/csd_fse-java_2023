package com.arijit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.arijit.model.Income;

public class IncomeDAOImpl implements IncomeDAO {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Income income) {
		String query = "insert into income (user_id, income_date, income_amount) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			System.out.println(con);
			ps = con.prepareStatement(query);
			ps.setString(1, income.getUserId());
			ps.setDate(2, income.getIncomeDate());
			ps.setDouble(3, income.getIncomeAmount());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("income saved with user_id=" + income.getUserId());
			} else
				System.out.println("invalid user_id=" + income.getUserId());
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
	public List<Income> getByUserId(String user_id) {
		String query = "select income_id, income_date, income_amount from income where user_id = ?";
		List<Income> incomeList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, user_id);
			rs = ps.executeQuery();
			while (rs.next()) {
				Income usr = new Income();
				usr.setIncomeId(rs.getInt("income_id"));
				usr.setUserId(user_id);
				usr.setIncomeDate(rs.getDate("income_date"));
				usr.setIncomeAmount(rs.getDouble("income_amount"));
				incomeList.add(usr);
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
		return incomeList;
	}

	@Override
	public List<Income> getByUserIdAndMonth(String user_id, int month) {
		String query = "select income_id, income_date, income_amount from income where user_id = ? and MONTH(income_date) = ?";
		List<Income> incomeList = new ArrayList<>();
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
				Income usr = new Income();
				usr.setIncomeId(rs.getInt("income_id"));
				usr.setUserId(user_id);
				usr.setIncomeDate(rs.getDate("income_date"));
				usr.setIncomeAmount(rs.getDouble("income_amount"));
				incomeList.add(usr);
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
		return incomeList;
	}

	@Override
	public List<Income> getByUserIdAndYear(String user_id, int year) {
		String query = "select income_id, income_date, income_amount from income where user_id = ? and YEAR(income_date) = ?";
		List<Income> incomeList = new ArrayList<>();
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
				Income usr = new Income();
				usr.setIncomeId(rs.getInt("income_id"));
				usr.setUserId(user_id);
				usr.setIncomeDate(rs.getDate("income_date"));
				usr.setIncomeAmount(rs.getDouble("income_amount"));
				incomeList.add(usr);
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
		return incomeList;
	}

	@Override
	public void updateByIncomeId(Income income, int income_id) {
		String query = "update income set user_id=?, income_date=?, income_amount=? where income_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, income.getUserId());
			ps.setDate(2, income.getIncomeDate());
			ps.setDouble(3, income.getIncomeAmount());
			ps.setInt(4, income_id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("income updated for income id " + income_id);
			} else
				System.out.println("Invalid income id!!");
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
	public void deleteByIncomeId(int income_id) {
		String query = "delete from income where income_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, income_id);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("income deleted with income id: " + income_id);
			} else
				System.out.println("Invalid income id!!");
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
