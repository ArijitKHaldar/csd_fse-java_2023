package com.arijit.idp;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.arijit.idp.entity.Login;
import com.arijit.idp.helpermodules.UserIdGenerator;
import com.arijit.idp.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PersonalFinanceTrackerApplication implements CommandLineRunner {

	@Autowired
	private LoginService loginService;

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		log.info("START");
		
//		Scanner sc = new Scanner(System.in);
//		System.out.println("## New Registration Details ##");
//		System.out.println("Enter email id: ");
//		String emailId = sc.next();
//		System.out.println("Enter password: ");
//		String password = sc.next();
//		sc.close();
//		UserIdGenerator uig = new UserIdGenerator();
//		String userId = uig.generateUserId(emailId);
//		loginService.insert(new Login(userId, emailId, password));
		
		Login loginDetailsByUserId = loginService.findLoginByUserId("abc");
		if(loginDetailsByUserId == null) {
			System.out.println("No users registered with given user id");
		}
		else {
			System.out.println(loginDetailsByUserId);
		}
//		Login loginDetailsByEmailId = loginService.findLoginByEmailId("arijit.haldar@gmail.com");		
//		if(loginDetailsByEmailId == null) {
//			System.out.println("No users registered with given email id");
//		}
//		else {
//			System.out.println(loginDetailsByEmailId);
//		}
		
		log.debug("Login Service", loginService);
		
		log.info("END");
	}
}
