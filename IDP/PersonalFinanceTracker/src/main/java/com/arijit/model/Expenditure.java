package com.arijit.model;

import java.sql.Date;

public class Expenditure {
	private int expenditureId;
	private String userId;
	private Date expenditureDate;
	private double expenditureAmount;

	public Expenditure() {
		super();
	}

	public Expenditure(int expenditureId, String userId, Date expenditureDate, double expenditureAmount) {
		super();
		this.expenditureId = expenditureId;
		this.userId = userId;
		this.expenditureDate = expenditureDate;
		this.expenditureAmount = expenditureAmount;
	}

	public int getExpenditureId() {
		return expenditureId;
	}

	public void setExpenditureId(int expenditureId) {
		this.expenditureId = expenditureId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getExpenditureDate() {
		return expenditureDate;
	}

	public void setExpenditureDate(Date expenditureDate) {
		this.expenditureDate = expenditureDate;
	}

	public double getExpenditureAmount() {
		return expenditureAmount;
	}

	public void setExpenditureAmount(double expenditureAmount) {
		this.expenditureAmount = expenditureAmount;
	}

	@Override
	public String toString() {
		return "Expenditure [expenditureId=" + expenditureId + ", userId=" + userId + ", expenditureDate="
				+ expenditureDate + ", expenditureAmount=" + expenditureAmount + "]";
	}

}
