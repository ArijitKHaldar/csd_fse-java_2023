package com.arijit.idp.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.arijit.idp.entity.Expenditure;

public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {

	public List<Expenditure> findByUserId(String userId);

	public List<Expenditure> findByUserIdAndExpenditureDate(String userId, Date expenditureDate);

	@Query("select e from Expenditure e join e.login l where l.userId = :userId and MONTH(e.expenditureDate) = :month")
	public List<Expenditure> findByUserIdAndMonth(@Param("userId") String userId, @Param("month") int month);

	@Query("select e from Expenditure e join e.login l where l.userId = :userId and YEAR(e.expenditureDate) = :year")
	public List<Expenditure> findByUserIdAndYear(@Param("userId") String userId, @Param("year") int year);

	@Query("SELECT e FROM Expenditure e JOIN e.category c WHERE e.userId = :userId AND c.expenditureTag = :expenditureTag")
	public List<Expenditure> findByUserIdAndCategory(@Param("userId") String userId,
			@Param("expenditureTag") String expenditureTag);

}
