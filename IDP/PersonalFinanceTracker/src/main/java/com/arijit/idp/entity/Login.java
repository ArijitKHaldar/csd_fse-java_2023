package com.arijit.idp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "login")
public class Login {
	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "email_id", nullable = false, unique = true)
	private String emailId;

	@Column(name = "password", nullable = false)
	private String password;

	public Login(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}
}
