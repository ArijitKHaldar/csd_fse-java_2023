package com.arijit.model;

import java.sql.Date;

public class Expenditure {
	private int expenditure_id;
	private String user_id;
	private Date expenditure_date;
	private double expenditure_amount;
	private String expenditure_tag;

	/**
	 * 
	 */
	public Expenditure() {
		super();
	}

	/**
	 * @param user_id
	 * @param expenditure_date
	 * @param expenditure_amount
	 * @param expenditure_tag
	 */
	public Expenditure(int expenditure_id, String user_id, Date expenditure_date, double expenditure_amount, String expenditure_tag) {
		super();
		this.expenditure_id = expenditure_id;
		this.user_id = user_id;
		this.expenditure_date = expenditure_date;
		this.expenditure_amount = expenditure_amount;
		this.expenditure_tag = expenditure_tag;
	}

	/**
	 * @return the expenditure_id
	 */
	public int getExpenditure_id() {
		return expenditure_id;
	}

	/**
	 * @param expenditure_id the expenditure_id to set
	 */
	public void setExpenditure_id(int expenditure_id) {
		this.expenditure_id = expenditure_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the expenditure_date
	 */
	public Date getExpenditure_date() {
		return expenditure_date;
	}

	/**
	 * @param expenditure_date the expenditure_date to set
	 */
	public void setExpenditure_date(Date expenditure_date) {
		this.expenditure_date = expenditure_date;
	}

	/**
	 * @return the expenditure_amount
	 */
	public double getExpenditure_amount() {
		return expenditure_amount;
	}

	/**
	 * @param expenditure_amount the expenditure_amount to set
	 */
	public void setExpenditure_amount(double expenditure_amount) {
		this.expenditure_amount = expenditure_amount;
	}

	/**
	 * @return the expenditure_tag
	 */
	public String getExpenditure_tag() {
		return expenditure_tag;
	}

	/**
	 * @param expenditure_tag the expenditure_tag to set
	 */
	public void setExpenditure_tag(String expenditure_tag) {
		this.expenditure_tag = expenditure_tag;
	}

	@Override
	public String toString() {
		return "Expenditure [expenditure_id=" + expenditure_id + ", user_id=" + user_id + ", expenditure_date="
				+ expenditure_date + ", expenditure_amount=" + expenditure_amount + ", expenditure_tag="
				+ expenditure_tag + "]";
	}

}
