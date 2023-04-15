package com.arijit.idp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arijit.idp.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String> {

//	@Query("select l from Login l where l.emailId = ?1")
	public Login findByEmailId(String emailId);
}
