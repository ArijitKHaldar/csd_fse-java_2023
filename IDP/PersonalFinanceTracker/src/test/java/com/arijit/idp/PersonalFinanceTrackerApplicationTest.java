package com.arijit.idp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@Suite
@SuiteDisplayName("JUnit Tests For Personal Finance Tracker")
@SelectPackages({ "com.arijit.idp.controller", "com.arijit.idp.helpermodules", "com.arijit.idp.service",
		"com.arijit.idp.usecases", "com.arijit.idp.exception" })

@SpringBootTest
public class PersonalFinanceTrackerApplicationTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertThat(context).isNotNull();
	}
}