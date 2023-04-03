package com.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.config.ApplicationConfig;
import com.spring.model.Candidate;
import com.spring.service.ElectionService;

public class Driver {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		ElectionService service = context.getBean(ElectionService.class);
		System.out.println("College Election Report");
		System.out.println("Total Number of Votes:" + service.getTotalVotes());
		System.out.println("Total Number of polled Votes:" + service.totalpolledVotes());
		System.out.println("Total Number of unpolled Votes:" + service.unpolledVotes());
		for (Candidate candidate : service.getVoteList()) {
			System.out.println(candidate);
		}
		Candidate winner = service.candidateWithMaximumVote();
		System.out.println("Election won by " + winner.getName() + " with " + winner.getNumberOfVotes() + " votes");
		((ConfigurableApplicationContext)context).close();
	}
}