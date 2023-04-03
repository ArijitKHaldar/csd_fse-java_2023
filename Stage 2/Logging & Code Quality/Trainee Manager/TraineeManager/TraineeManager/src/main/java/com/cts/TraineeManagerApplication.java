package com.cts;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cts.entity.Trainee;
import com.cts.exception.InvalidTraineeDataException;
import com.cts.exception.InvalidTraineeoperationException;
import com.cts.service.TManagerService;

@SpringBootApplication
public class TraineeManagerApplication {

	public static void main(String[] args) throws InvalidTraineeDataException, InvalidTraineeoperationException {
		
		ApplicationContext ctx=	SpringApplication.run(TraineeManagerApplication.class, args);
		TManagerService tr=(TManagerService) ctx.getBean("trainee");
		Logger logger=LoggerFactory.getLogger(TraineeManagerApplication.class);
	    
		
		tr.createTrainee(new Trainee("Maria","mariasajan@gmail.com","ABA90AA09"));
		logger.info("Registering new trainee");
		
	
		Trainee t=tr.findByTName("Maria");
		System.out.println(t);
		
		
		List<Trainee> li=tr.getAllTrainee();
		System.out.println(li);
		
		String s=tr.removeTrainee(1);
		System.out.println(s);
	} 

}
