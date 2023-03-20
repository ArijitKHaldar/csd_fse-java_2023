package com.spring.app;

import java.util.Calendar;
import java.util.Date;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;

@Configuration
@ComponentScan
public class ApplicationConfig {
    
    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setEmpId(1234);
        employee.setEmpName("Arijit");
        
        return employee;
    }
    
    @Bean
    public Passport passObj() {
        Passport pp = new Passport();
        
        Date todayDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(todayDate);
        cal.add(Calendar.DATE, 84);
        
        Date expiryDate = cal.getTime();
        pp.setPassNum(2345);
        
        pp.setDateOfIssue(todayDate);
        pp.setDateOfExpiry(expiryDate);
        
        return pp;
    }
	
}
