package com.arijit.idp;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arijit.idp.entity.Income;
import com.arijit.idp.entity.Login;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.service.CategoryService;
import com.arijit.idp.service.ExpenditureService;
import com.arijit.idp.service.IncomeService;
import com.arijit.idp.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PersonalFinanceTrackerApplication implements CommandLineRunner {

	@Autowired
	private LoginService loginService;
	
	@Autowired
	private IncomeService incomeService;
	
//	@Autowired
//	private ExpenditureService expenditureService;
//	
//	@Autowired
//	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("START");
		/*
		 * Login Table
		 */
		//
		// Create
		//
//		Scanner sc = new Scanner(System.in);
//		System.out.println("## New Registration Details ##");
//		System.out.println("Enter email id: ");
//		String emailId = sc.next();
//		System.out.println("Enter password: ");
//		String password = sc.next();
//		sc.close();
//		UserIdGenerator uig = new UserIdGenerator();
//		String userId = uig.generateUserId(emailId);
//		loginService.signup(new Login(userId, emailId, password));
		//
		// Retrieve
		//
//		Login loginDetailsByUserId = loginService.findLoginByUserId("a2b0c20300415085757");
//		if(loginDetailsByUserId == null) {
//			System.out.println("No users registered with given user id");
//		}
//		else {
//			System.out.println(loginDetailsByUserId);
//		}
		
//		Login loginDetailsByEmailId = loginService.findLoginByEmailId("abc@domain.com");		
//		if(loginDetailsByEmailId == null) {
//			System.out.println("No users registered with given email id");
//		}
//		else {
//			System.out.println(loginDetailsByEmailId);
//		}
		
//		log.debug("Login Service", loginService);
		
		
		/*
		 * Income Table
		 */
		
		//
		//Create
		//
		String emailId = "abc@domain.com";
		Login loginTmp = loginService.findLoginByEmailId(emailId);
//		Income incomeTmp = new Income();
//		incomeTmp.setLogin(loginTmp);
//		incomeTmp.setUserId(loginTmp.getUserId());
//		incomeTmp.setIncomeDate(Date.valueOf("2023-05-12"));
//		incomeTmp.setIncomeAmount(new BigDecimal(30000.00));
//		incomeService.insertIncome(incomeTmp);
		
		//
		//Retrieve
		//
//		List<Income> incomeDetailsByUserId = incomeService.findByUserId(loginTmp.getUserId());
//		if(incomeDetailsByUserId == null) {
//			System.out.println("No users registered with given user id");
//		}
//		else {
//			for(Income i : incomeDetailsByUserId) {
//				System.out.println("Income Id: " + i.getIncomeId());
//				System.out.println("User Id: " + i.getLogin().getUserId()); //To Solve Lazy Loading Issue
//		        System.out.println("Income Date: " + i.getIncomeDate());
//		        System.out.println("Income Amount: " + i.getIncomeAmount());
//			}
//		}
		
//		List<Income> incomeDetailsByUserIdAndMonth = incomeService.findByUserIdAndMonth(loginTmp.getUserId(),5);
//		if(incomeDetailsByUserIdAndMonth.isEmpty()) {
//			System.out.println("No users registered with given user id");
//		}
//		else {
//			for(Income i : incomeDetailsByUserIdAndMonth) {
//				System.out.println("Income Id: " + i.getIncomeId());
//				System.out.println("User Id: " + i.getLogin().getUserId()); //To Solve Lazy Loading Issue
//		        System.out.println("Income Date: " + i.getIncomeDate());
//		        System.out.println("Income Amount: " + i.getIncomeAmount());
//			}
//		}
		
		
		//
		// Update
		//
//		System.out.println(incomeService.updateByIncomeId(1, new Income(loginTmp,Date.valueOf("2023-04-03"),new BigDecimal(700))));
		
		//
		// Delete
		//
//		System.out.println(incomeService.deleteByIncomeId(3));
//		log.debug("Income Service", incomeService);
		
		
		
		/*
		 * Expenditure and Category Table
		 */
//		log.debug("Expenditure Service", expenditureService);
//		log.debug("Category Service", categoryService);
		log.info("END");
	}
}
