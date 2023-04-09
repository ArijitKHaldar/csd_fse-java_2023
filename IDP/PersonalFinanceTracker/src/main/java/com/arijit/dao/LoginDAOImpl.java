package com.arijit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.arijit.model.Login;

public class LoginDAOImpl implements LoginDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Login user) {
	    String query = "insert into login (email_id, password) values (?,?)";
	    Connection con = null;
	    PreparedStatement ps = null;
	    try {
	      con = dataSource.getConnection();
	      System.out.println(con);
	      ps = con.prepareStatement(query);
	      ps.setString(1, user.getEmail_id());
	      ps.setString(2, user.getPassword());
	      int out = ps.executeUpdate();
	      if (out != 0) {
	        System.out.println("user saved with email-id=" + user.getEmail_id());
	      } else System.out.println("invalid id=" + user.getEmail_id());
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
	public Login getByEmailId(String email_id) {
	    String query = "select password from login where email_id = ?";
	    Login usr = null;
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    try {
	      con = dataSource.getConnection();
	      ps = con.prepareStatement(query);
	      ps.setString(1, email_id);
	      rs = ps.executeQuery();
	      if (rs.next()) {
	        usr = new Login();
	        usr.setEmail_id(email_id);
	        usr.setPassword(rs.getString("password"));
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
