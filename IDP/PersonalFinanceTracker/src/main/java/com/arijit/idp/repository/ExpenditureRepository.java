package com.arijit.idp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arijit.idp.entity.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer>{
	@Query("select e from Expenditure e join e.login l where l.userId = :userId")
	public List<Expenditure> findByUserId(@Param("userId")String userId);
	
	@Query("select e from Expenditure e join e.login l where l.userId = :userId and MONTH(e.expenditureDate) = :month")
	public List<Expenditure> findByUserIdAndMonth(@Param("userId")String userId, @Param("month") int month);
	
	@Query("select e from Expenditure e join e.login l where l.userId = :userId and YEAR(e.expenditureDate) = :year")
	public List<Expenditure> findByUserIdAndYear(@Param("userId")String userId, @Param("year") int year);
}
