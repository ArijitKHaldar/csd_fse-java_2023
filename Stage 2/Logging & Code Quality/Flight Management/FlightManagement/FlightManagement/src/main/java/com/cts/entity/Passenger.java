package com.cts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Passenger {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int pid;
	private String name;
	private boolean vip;

	public Passenger(String name, boolean vip) {
		super();
		this.name = name;
		this.vip = vip;
	}

	public Passenger() {

	}

}
