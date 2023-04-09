package com.arijit.model;

public class Category {
	private int categoryId;
	private String expenditureTag;
	private int expenditureId;

	public Category() {
		super();
	}

	public Category(int categoryId, String expenditureTag, int expenditureId) {
		super();
		this.categoryId = categoryId;
		this.expenditureTag = expenditureTag;
		this.expenditureId = expenditureId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getExpenditureTag() {
		return expenditureTag;
	}

	public void setExpenditureTag(String expenditureTag) {
		this.expenditureTag = expenditureTag;
	}

	public int getExpenditureId() {
		return expenditureId;
	}

	public void setExpenditureId(int expenditureId) {
		this.expenditureId = expenditureId;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", expenditureTag=" + expenditureTag + ", expenditureId="
				+ expenditureId + "]";
	}
	
}
