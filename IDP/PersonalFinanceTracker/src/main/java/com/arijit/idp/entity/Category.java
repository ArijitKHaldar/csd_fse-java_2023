package com.arijit.idp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Setter
@ToString
@Entity
@Table(name = "category")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Enumerated(EnumType.STRING)
	@Column(name = "expenditure_tag")
	private ExpenditureTag expenditureTag;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expenditure_id", nullable = false)
	@JsonIgnore
	private Expenditure expenditure;

	public Category(ExpenditureTag expenditureTag, Expenditure expenditure) {
		this.expenditureTag = expenditureTag;
		this.expenditure = expenditure;
	}

	public enum ExpenditureTag {
		FOOD, UTILITIES, HOUSING, TRANSPORTATION, EDUCATION, CLOTHING, MEDICAL, INSURANCE, HOUSEHOLD, PERSONAL, DEBT,
		DONATION, ENTERTAINMENT
	}
}
