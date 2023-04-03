package com.cts.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="trainee")
@Getter  
@Setter
@ToString
public class Trainee {
	@Id
	@GeneratedValue
	private int empId;
	private String name;
	private String email;
	private String cohortcode;

	public Trainee( String name, String email, String cohortcode) {
		super();
		this.empId = empId;
		this.name = name;
		this.email = email;
		this.cohortcode = cohortcode;
	}
	public Trainee() {
		
	}
	
	
}
