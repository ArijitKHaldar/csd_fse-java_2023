package com.arijit.idp.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Login login;

	// @Temporal(TemporalType.DATE) can only be used for java.util.Date
	@Getter
	@Setter
	@Column(name = "income_date")
	private Date incomeDate;

	@Getter
	@Setter
	@Column(name = "income_amount", precision = 10, scale = 2)
	private BigDecimal incomeAmount;

	public Income(Date incomeDate, BigDecimal incomeAmount) {
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}
}
