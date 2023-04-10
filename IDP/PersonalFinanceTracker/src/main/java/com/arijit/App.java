package com.arijit;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.arijit.dao.CategoryDAO;
import com.arijit.dao.ExpenditureDAO;
import com.arijit.dao.IncomeDAO;
import com.arijit.dao.LoginDAO;
import com.arijit.model.Category;
import com.arijit.model.Expenditure;
import com.arijit.model.Income;
import com.arijit.model.Login;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("springJDBC.xml");

		Login userLogin = new Login();
		Income income_POJO = new Income();
		Expenditure expenditure_POJO = new Expenditure();
		Category category_POJO = new Category();

		LoginDAO login = ctx.getBean("loginDAO", LoginDAO.class);
		IncomeDAO income = ctx.getBean("incomeDAO", IncomeDAO.class);
		ExpenditureDAO expenditure = ctx.getBean("expenditureDAO", ExpenditureDAO.class);
		CategoryDAO category = ctx.getBean("categoryDAO", CategoryDAO.class);

		/*
		 * Login Table
		 */
//		 String emailId = "abc@domain.com";
//		 userLogin.setEmailId(emailId);
//		 userLogin.setPassword("SuperSecretPassword");

		// Create
//		 login.insert(userLogin,emailId);

		// Retrieve
//		Login loginDetailsByEmailId = login.getByEmailId("abc@domain.com");
//		if (loginDetailsByEmailId == null) {
//			System.out.println("No users registered with given email id");
//		}
		
		
//		Login loginDetailsByUserId = login.getByUserId("a2r0i2j3i0409200325");
//		if (loginDetailsByUserId == null) {
//			System.out.println("No users registered with given user id");
//		}

		
		
		
		
		
		/*
		 * Income Table
		 */
//		income_POJO.setUserId("a2b0c20300409200526");
		//////ps.setDate(2, java.sql.Date.valueOf("2013-09-04"));
		//////ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
//		income_POJO.setIncomeDate(Date.valueOf("2023-04-01"));
//		income_POJO.setIncomeAmount(30);

		// Create
//		income.insert(income_POJO);

		//Retrieve
//		List<Income> userIncomeByUserId = income.getByUserId("a2b0c20300409200526");
//		if (userIncomeByUserId.isEmpty()) {
//			System.out.println("No income details available for selected user");
//		}
//		else {
//			for(Income i : userIncomeByUserId) {
//				System.out.println(i);
//			}
//		}
		
		
//		List<Income> userIncomeByUserIdAndMonthOfIncome = income.getByUserIdAndMonth("a2b0c20300409200526", 4);
//		if (userIncomeByUserIdAndMonthOfIncome.isEmpty()) {
//			System.out.println("No income details available for selected user on the given month");
//		}
//		else {
//			for(Income i : userIncomeByUserIdAndMonthOfIncome) {
//				System.out.println(i);
//			}
//		}
		

//		List<Income> userIncomeByUserIdAndYearOfIncome = income.getByUserIdAndYear("a2b0c20300409200526", 2023);
//		if (userIncomeByUserIdAndYearOfIncome.isEmpty()) {
//			System.out.println("No income details available for selected user on the given year");
//		}
//		else {
//			for(Income i : userIncomeByUserIdAndYearOfIncome) {
//				System.out.println(i);
//			}
//		}
		
		
		// Update
//		income_POJO.setUserId("a2b0c20300409200526");
//		income_POJO.setIncomeAmount(10);
//		income_POJO.setIncomeDate(Date.valueOf("2023-04-07"));
//		income.updateByIncomeId(income_POJO, 2);
		
		
		// Delete
//		income.deleteByIncomeId(4);
		
		

		/*
		 * Expenditure Table & Category
		 */
		// Create
//		expenditure_POJO.setUserId("a2b0c20300409200526");
//		expenditure_POJO.setExpenditureDate(Date.valueOf("2023-04-07"));
//		expenditure_POJO.setExpenditureAmount(500);
//		int expenditureId = expenditure.insert(expenditure_POJO);
//		category_POJO.setExpenditureTag("clothing");
//		category_POJO.setExpenditureId(expenditureId);
//		category.insert(category_POJO);
		
		//Retrieve
//		List<Expenditure> userExpenditureByUserId = expenditure.getByUserId("a2b0c20300409200526");
//		if (userExpenditureByUserId.isEmpty()) {
//			System.out.println("No expenditure details available for selected user");
//		}
//		else {
//			for(Expenditure i : userExpenditureByUserId) {
//				System.out.println(i);
//				Category tags = category.getByExpenditureId(i.getExpenditureId());
//			    if(tags!=null) {
//			        System.out.println(tags);
//			    }
//			}
//		}
		
		
//		List<Expenditure> userExpenditureByUserIdAndMonthOfIncome = expenditure
//				.getByUserIdAndMonth("a2b0c20300409200526", 4);
//		if (userExpenditureByUserIdAndMonthOfIncome.isEmpty()) {
//			System.out.println("No expenditure details available for selected user on the given month");
//		} else {
//			for (Expenditure i : userExpenditureByUserIdAndMonthOfIncome) {
//				System.out.println(i);
//				Category tags = category.getByExpenditureId(i.getExpenditureId());
//				if (tags != null) {
//					System.out.println(tags);
//				}
//			}
//		}
		

//		List<Expenditure> userExpenditureByUserIdAndYearOfIncome = expenditure.getByUserIdAndYear("a2b0c20300409200526",
//				2023);
//		if (userExpenditureByUserIdAndYearOfIncome.isEmpty()) {
//			System.out.println("No expenditure details available for selected user on the given year");
//		} else {
//			for (Expenditure i : userExpenditureByUserIdAndYearOfIncome) {
//				System.out.println(i);
//				Category tags = category.getByExpenditureId(i.getExpenditureId());
//				if (tags != null) {
//					System.out.println(tags);
//				}
//			}
//		}
		
		
		// Update
//		expenditure_POJO.setUserId("a2b0c20300409200526");
//		expenditure_POJO.setExpenditureDate(Date.valueOf("2023-04-07"));
//		expenditure_POJO.setExpenditureAmount(1000);
//		category_POJO.setCategoryId(1);
//		category_POJO.setExpenditureTag("transportation");
//		expenditure.updateByExpenditureId(expenditure_POJO, 11);
//		category.updateByExpenditureId(category_POJO, 11);
		
		
		// Delete
//		category.deleteByExpenditureId(11);
//		expenditure.deleteByExpenditureId(11);

		((AbstractApplicationContext) ctx).close();
	}

}
