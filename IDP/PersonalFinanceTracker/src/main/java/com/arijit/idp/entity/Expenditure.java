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
@Getter
@ToString
@Entity
@Table(name = "expenditure")
public class Expenditure {

	@Id
	@GeneratedValue
	@Column(name = "expenditure_id")
	private int expenditureId;

	@Setter
	@Column(name = "user_id")
	private String userId; //Foreign key column linking Login

	@Setter
	@ManyToOne(fetch = FetchType.LAZY) // Many Expenditure for one Login or user
	@JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private Login login;

	@Setter
	@Column(name = "expenditure_date")
	private Date expenditureDate;

	@Setter
	@Column(name = "expenditure_amount", precision = 10, scale = 2)
	private BigDecimal expenditureAmount;

	public Expenditure(String userId, Date expenditureDate, BigDecimal expenditureAmount) {
		this.userId = userId;
		this.expenditureDate = expenditureDate;
		this.expenditureAmount = expenditureAmount;
	}
}
