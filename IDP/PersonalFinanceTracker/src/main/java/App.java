import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Login;
import model.Income;
import model.Expenditure;
import model.Category;

import dao.LoginDAO;
import dao.IncomeDAO;
import dao.ExpenditureDAO;
import dao.CategoryDAO;

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

		//userLogin.setEmail_id("abc@domain.com");
		//userLogin.setPassword("SuperSecretPassword");

		// Create
		//login.insert(userLogin);

		// Retrieve
		Login user_login = login.getByEmailId("abc@domain.com");
		if (user_login == null) {
			System.out.println("No users registered");
		}
		((AbstractApplicationContext) ctx).close();
	}

}
