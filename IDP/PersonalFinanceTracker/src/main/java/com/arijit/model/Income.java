package com.arijit.model;

import java.sql.Date;

public class Income {
	private int incomeId;
	private String userId;
	private Date incomeDate;
	private double incomeAmount;

	public Income() {
		super();
	}

	public Income(int incomeId, String userId, Date incomeDate, double incomeAmount) {
		super();
		this.incomeId = incomeId;
		this.userId = userId;
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}

	public int getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(int incomeId) {
		this.incomeId = incomeId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public double getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	@Override
	public String toString() {
		return "Income [incomeId=" + incomeId + ", userId=" + userId + ", incomeDate=" + incomeDate + ", incomeAmount="
				+ incomeAmount + "]";
	}

}
