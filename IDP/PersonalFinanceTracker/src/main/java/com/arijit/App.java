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

		// userLogin.setEmail_id("abc@domain.com");
		// userLogin.setPassword("SuperSecretPassword");

		// Create
		// login.insert(userLogin);

		// Retrieve
//		Login user_login = login.getByEmailId("abc@domain.com");
//		if (user_login == null) {
//			System.out.println("No users registered");
//		}

		
		
		
		
		
		/*
		 * Income Table
		 */
//		income_POJO.setUser_id("abc@domain.in");
		//////ps.setDate(2, java.sql.Date.valueOf("2013-09-04"));
		//////ps.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
//		income_POJO.setIncome_date(Date.valueOf("2023-04-07"));
//		income_POJO.setIncome_amount(300);

		// Create
//		income.insert(income_POJO);

		//Retrieve
//		List<Income> userIncomeByUserId = income.getByUserId("abc@domain.com");
//		if (userIncomeByUserId.isEmpty()) {
//			System.out.println("No income details available for selected user");
//		}
//		else {
//			for(Income i : userIncomeByUserId) {
//				System.out.println(i);
//			}
//		}
		
		
//		List<Income> userIncomeByUserIdAndMonthOfIncome = income.getByUserIdAndMonth("abc@domain.in", 4);
//		if (userIncomeByUserIdAndMonthOfIncome.isEmpty()) {
//			System.out.println("No income details available for selected user on the given date");
//		}
//		else {
//			for(Income i : userIncomeByUserIdAndMonthOfIncome) {
//				System.out.println(i);
//			}
//		}
		
		
		// Update
//		income_POJO.setUser_id("abc@domain.com");
//		income_POJO.setIncome_amount(10);
//		income_POJO.setIncome_date(Date.valueOf("2023-04-07"));
//		income.updateByIncomeId(income_POJO, 2);
		
		
		// Delete
//		income.deleteByIncomeId(4);

		((AbstractApplicationContext) ctx).close();
	}

}
