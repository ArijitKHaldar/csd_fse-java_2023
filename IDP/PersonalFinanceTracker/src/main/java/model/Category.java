package model;

public class Category {
	private String user_id;
	private String expenditure_tag;
	private int expenditure_id;

	/**
	 * 
	 */
	public Category() {
		super();
	}

	/**
	 * @param user_id
	 * @param expenditure_tag
	 * @param expenditure_id
	 */
	public Category(String user_id, String expenditure_tag, int expenditure_id) {
		super();
		this.user_id = user_id;
		this.expenditure_tag = expenditure_tag;
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

	@Override
	public String toString() {
		return "Category [user_id=" + user_id + ", expenditure_tag=" + expenditure_tag + ", expenditure_id="
				+ expenditure_id + "]";
	}
}
