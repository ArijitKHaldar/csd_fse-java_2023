package com.arijit.idp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arijit.idp.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

	@Query("select login from Login login where login.userId = ?1")
	public Login findLoginByUserId(String userId);

	@Query("select login from Login login where login.emailId = ?1")
	public Login findLoginByEmailId(String emailId);
}
