package com.arijit.idp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arijit.idp.entity.Income;

public interface IncomeRepository extends JpaRepository<Income, Integer> {
	
	@Query("select i from Income i join i.login l where l.userId = :userId")
	public List<Income> findByUserId(@Param("userId") String userId);
	
	@Query("select i from Income i join i.login l where l.userId = :userId and MONTH(i.incomeDate) = :month")
	public List<Income> findByUserIdAndMonth(@Param("userId")String userId, @Param("month") int month);
	
	@Query("select i from Income i join i.login l where l.userId = :userId and YEAR(i.incomeDate) = :year")
	public List<Income> findByUserIdAndYear(@Param("userId")String userId, @Param("year") int year);
	
}
