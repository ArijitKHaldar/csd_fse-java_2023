package com.arijit.idp.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "income")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "income_id", nullable = false)
	private int incomeId;

	@Setter
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private Login login;

	// @Temporal(TemporalType.DATE) can only be used for java.util.Date
	@Setter
	@Column(name = "income_date")
	private Date incomeDate;

	@Setter
	@Column(name = "income_amount", precision = 10, scale = 2)
	private BigDecimal incomeAmount;

	public Income(String userId, Date incomeDate, BigDecimal incomeAmount) {
		this.userId = userId;
		this.incomeDate = incomeDate;
		this.incomeAmount = incomeAmount;
	}
}
