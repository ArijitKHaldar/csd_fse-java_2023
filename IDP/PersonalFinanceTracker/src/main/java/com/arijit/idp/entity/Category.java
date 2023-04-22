package com.arijit.idp.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private int categoryId;

	@Setter
	@Enumerated(EnumType.STRING)
	@Column(name = "expenditure_tag", columnDefinition = "ENUM('FOOD', 'UTILITIES', 'HOUSING', 'TRANSPORTATION', 'EDUCATION', 'CLOTHING', 'MEDICAL', 'INSURANCE', 'HOUSEHOLD', 'PERSONAL', 'DEBT', 'DONATION', 'ENTERTAINMENT')")
	private ExpenditureTag expenditureTag;

	@Setter
	@Column(name = "expenditure_id")
	private int expenditureId; // Foreign key column linking Expenditure

	@Setter
	@OneToMany(fetch = FetchType.LAZY) // One Category for Many Expenditure
	@JoinColumn(name = "expenditure_id", nullable = false, insertable = false, updatable = false)
	@JsonIgnore
	private List<Expenditure> expenditure;

	public Category(ExpenditureTag expenditureTag) {
		this.expenditureTag = expenditureTag;
	}

	public enum ExpenditureTag {
		FOOD, UTILITIES, HOUSING, TRANSPORTATION, EDUCATION, CLOTHING, MEDICAL, INSURANCE, HOUSEHOLD, PERSONAL, DEBT,
		DONATION, ENTERTAINMENT
	}
}
