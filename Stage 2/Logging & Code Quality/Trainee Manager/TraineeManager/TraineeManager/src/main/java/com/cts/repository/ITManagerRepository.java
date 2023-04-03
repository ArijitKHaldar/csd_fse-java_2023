package com.cts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.entity.Trainee;

public interface ITManagerRepository extends JpaRepository <Trainee,Integer>{

	public Trainee findByName(String name);

}
