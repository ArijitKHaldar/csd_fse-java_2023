package com.cts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import com.cts.entity.Flight;
import com.cts.entity.Passenger;
import com.cts.service.FlightService;

@SpringBootApplication
public class FlightManagement2Application {

	public static void main(String[] args) {
		Logger logger=LoggerFactory.getLogger(FlightManagement2Application.class);
		logger.info("First log message");
		
		ApplicationContext ctx= SpringApplication.run(FlightManagement2Application.class, args);
		FlightService fs=(FlightService)ctx.getBean("fliService");
		String val = fs.createFlight("f1","business");
		System.out.println(val);
		logger.info("New flight object created");
		
		
		Flight f = fs.findFlight("f1");
		boolean result = fs.addPassenger(new Passenger("maria",true), f);
		boolean result2 = fs.addPassenger(new Passenger("Lina",true), f);
		boolean result3 = fs.addPassenger(new Passenger("shiv",false), f);
		boolean result4 = fs.addPassenger(new Passenger("Riya",true), f);
		System.out.println(result);
		logger.info("Adding passenger to the flight");
		
		Passenger p = fs.findPassenger("maria");
		boolean res = fs.removePassenger(p, f);
		System.out.println(res);
		logger.info("Removing passenger from flight passengers list");
	}

}
