package com.arijit.idp;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@Suite
@SuiteDisplayName("JUnit Tests For Personal Finance Tracker")
@SelectPackages({ "com.arijit.idp.service", "com.arijit.idp.usecases" })
@SpringBootTest
public class PersonalFinanceTrackerApplicationTest {

	@Test
	void contextLoads() {

	}
}