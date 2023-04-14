package com.arijit.idp.entity;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class Expenditure {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expenditureId;
	private String userId;
	private Date expenditureDate;
	private double expenditureAmount;
}
