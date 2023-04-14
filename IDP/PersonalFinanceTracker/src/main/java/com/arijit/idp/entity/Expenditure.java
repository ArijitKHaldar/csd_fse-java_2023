package com.arijit.idp.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "expenditure")
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "expenditure_id")
	private int expenditureId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Login login;

	@Column(name = "expenditure_date")
	private Date expenditureDate;

	@Column(name = "expenditure_amount")
	private double expenditureAmount;

	@OneToOne(mappedBy = "expenditure", cascade = CascadeType.ALL, orphanRemoval = true)
	private Category category;
}
