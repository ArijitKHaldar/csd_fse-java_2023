package com.arijit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.arijit.model.Login;
import com.arijit.service.UserIdGenerator;

public class LoginDAOImpl implements LoginDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Login user, String emailId) {
		UserIdGenerator genUserId = new UserIdGenerator();
		user.setUserId(genUserId.generateUserId(emailId));
		String query = "insert into login (user_id, email_id, password) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			System.out.println(con);
			ps = con.prepareStatement(query);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getEmailId());
			ps.setString(3, user.getPassword());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("user saved with user id=" + user.getUserId());
			} else
				System.out.println("invalid email id=" + user.getEmailId());
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
	public Login getByEmailId(String emailId) {
		String query = "select user_id, password from login where email_id = ?";
		Login usr = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, emailId);
			rs = ps.executeQuery();
			if (rs.next()) {
				usr = new Login();
				usr.setUserId(rs.getString("user_id"));
				usr.setPassword(rs.getString("password"));
				usr.setEmailId(emailId);
				System.out.println(usr);
			} else {
				System.out.println("user not found!!");
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
		return usr;
	}

	@Override
	public Login getByUserId(String userId) {
		String query = "select email_id, password from login where user_id = ?";
		Login usr = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (rs.next()) {
				usr = new Login();
				usr.setEmailId(rs.getString("email_id"));
				usr.setPassword(rs.getString("password"));
				usr.setUserId(userId);
				System.out.println(usr);
			} else {
				System.out.println("user not found!!");
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
		return usr;
	}

}
