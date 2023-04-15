package com.arijit.idp.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "income")
public class Income {

	@Getter
	@Id
	@GeneratedValue
	@Column(name = "income_id")
	private int incomeId;

	@Getter
	@Setter
	@Column(name = "user_id")
	private String userId;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private Login login;

	// @Temporal(TemporalType.DATE) can only be used for java.util.Date
	@Getter
	@Setter
	@Column(name = "income_date")
	private Date incomeDate;

	@Getter
	@Setter
	@Column(name = "income_amount")
	private double incomeAmount;

	// For user specific queries
	public Income(String userId, Date incomeDate, double incomeAmount) {
		super();
		this.userId = userId;
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}

	// For individual entry specific queries
	public Income(int incomeId, Date incomeDate, double incomeAmount) {
		super();
		this.incomeId = incomeId;
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}
}
