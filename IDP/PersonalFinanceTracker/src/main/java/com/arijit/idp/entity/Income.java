package com.arijit.idp.entity;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Income {

	@Id
	@GeneratedValue
	@Getter
	private int incomeId;

	@Getter
	@Setter
	private String userId;

	@Getter
	@Setter
	private Date incomeDate;

	@Getter
	@Setter
	private double incomeAmount;

	public Income(String userId, Date incomeDate, double incomeAmount) {
		super();
		this.userId = userId;
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}

}
