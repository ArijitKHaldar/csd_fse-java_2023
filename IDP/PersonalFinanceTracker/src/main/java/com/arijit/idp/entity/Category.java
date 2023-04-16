package com.arijit.idp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "category")
public class Category {

	@Getter
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int categoryId;

	@Getter
	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "expenditure_tag", columnDefinition = "ENUM('FOOD', 'UTILITIES', 'HOUSING', 'TRANSPORTATION', 'EDUCATION', 'CLOTHING', 'MEDICAL', 'INSURANCE', 'HOUSEHOLD', 'PERSONAL', 'DEBT', 'DONATION', 'ENTERTAINMENT')")
	private ExpenditureTag expenditureTag;

	@Getter
	@Setter
	@GeneratedValue
	@Column(name = "expenditure_id")
	private int expenditureId;

	@Getter
	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "expenditure_id", nullable = false, insertable = false, updatable = false)
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
