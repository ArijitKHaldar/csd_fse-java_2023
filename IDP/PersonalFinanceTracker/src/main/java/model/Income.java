package model;

import java.sql.Date;

public class Income {
	private String user_id;
	private Date income_date;
	private double income_amount;

	/**
	 * 
	 */
	public Income() {
		super();
	}

	/**
	 * @param user_id
	 * @param income_date
	 * @param income_amount
	 */
	public Income(String user_id, Date income_date, double income_amount) {
		super();
		this.user_id = user_id;
		this.income_date = income_date;
		this.income_amount = income_amount;
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
	 * @return the income_date
	 */
	public Date getIncome_date() {
		return income_date;
	}

	/**
	 * @param income_date the income_date to set
	 */
	public void setIncome_date(Date income_date) {
		this.income_date = income_date;
	}

	/**
	 * @return the income_amount
	 */
	public double getIncome_amount() {
		return income_amount;
	}

	/**
	 * @param income_amount the income_amount to set
	 */
	public void setIncome_amount(double income_amount) {
		this.income_amount = income_amount;
	}

	@Override
	public String toString() {
		return "Income [user_id=" + user_id + ", income_date=" + income_date + ", income_amount=" + income_amount + "]";
	}

}
