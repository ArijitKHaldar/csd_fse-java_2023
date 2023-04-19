package com.arijit.idp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arijit.idp.entity.Expenditure;
import com.arijit.idp.repository.ExpenditureRepository;

@Transactional(label = "handlingExpenditureAndCategory")
@Service
public class ExpenditureServiceImpl {
	
	@Autowired
	private ExpenditureRepository expenditureRepository;
	
	// Create
	public void insertExpenditure(Expenditure expenditure) {
		expenditureRepository.save(expenditure);
	}
}
