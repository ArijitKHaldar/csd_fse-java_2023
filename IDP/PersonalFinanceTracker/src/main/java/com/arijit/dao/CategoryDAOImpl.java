package com.arijit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.arijit.model.Category;

public class CategoryDAOImpl implements CategoryDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void insert(Category category) {
		String query = "insert into category (category_id, expenditure_tag, expenditure_id) values (?,?,?)";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			System.out.println(con);
			ps = con.prepareStatement(query);
			ps.setInt(1, category.getCategoryId());
			ps.setString(2, category.getExpenditureTag());
			ps.setInt(3, category.getExpenditureId());
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("expenditure saved for category id=" + category.getCategoryId());
			} else
				System.out.println("invalid expenditure id=" + category.getExpenditureId());
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
	public List<Category> getByExpenditureId(int expenditureId) {
		String query = "select category_id, expenditure_tag from category where expenditure_id = ?";
		List<Category> categoryList = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expenditureId);
			rs = ps.executeQuery();
			while (rs.next()) {
				Category usr = new Category();
				usr.setCategoryId(rs.getInt("category_id"));
				usr.setExpenditureTag(rs.getString("expenditure_tag"));
				usr.setExpenditureId(expenditureId);
				categoryList.add(usr);
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
		return categoryList;
	}

	@Override
	public void updateByExpenditureId(Category category, int expenditureId) {
		String query = "update category set category_id=?, expenditure_tag=? where expenditure_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, category.getCategoryId());
			ps.setString(2, category.getExpenditureTag());
			ps.setInt(3, expenditureId);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("category updated for expenditure id " + expenditureId);
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
		String query = "delete from category where expenditure_id=?";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, expenditureId);
			int out = ps.executeUpdate();
			if (out != 0) {
				System.out.println("category deleted with expenditure id: " + expenditureId);
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
