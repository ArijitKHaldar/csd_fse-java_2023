package com.arijit.idp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class PersonalFinanceTrackerApplication implements CommandLineRunner {

//	@Autowired
//	BudgetForecasting calculate;

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceTrackerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		log.info("~~.~~ APPLICATION STARTED SUCCESSFULLY ~~.~~");

//		double val = calculate.predictSavings("n2a0m2e300423205511", Date.valueOf("2023-05-01"));
//		System.out.println(val);
	}
}
