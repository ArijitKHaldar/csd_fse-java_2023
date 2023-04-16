package com.arijit.idp.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Entity
@Table(name = "expenditure")
public class Expenditure {

	@Getter
	@Id
	@GeneratedValue
	@Column(name = "expenditure_id")
	private int expenditureId;

	@Getter
	@Setter
	@Column(name = "user_id")
	private String userId;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	private Login login;

	@Getter
	@Setter
	@Column(name = "expenditure_date")
	private Date expenditureDate;

	@Getter
	@Setter
	@Column(name = "expenditure_amount", precision = 10, scale = 2)
	private BigDecimal expenditureAmount;

	@Getter
	@Setter
	@OneToOne(mappedBy = "expenditure", cascade = CascadeType.ALL, orphanRemoval = true)
	private Category category;
	
	public Expenditure(Login login, Date expenditureDate, BigDecimal expenditureAmount, Category category) {
		this.login = login;
		this.expenditureDate = expenditureDate;
		this.expenditureAmount = expenditureAmount;
		this.category = category;
	}
}
