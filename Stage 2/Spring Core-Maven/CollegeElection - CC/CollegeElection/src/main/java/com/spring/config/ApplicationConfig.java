package com.spring.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.spring.model.Candidate;

//Use appropriate annotation
@Configuration
@ComponentScan(basePackages = "com.spring")
@PropertySource("totalVotes.properties")
public class ApplicationConfig {
	@Bean
	public ArrayList<Candidate> getVoteList() {
		ArrayList<Candidate> voteList = new ArrayList<>();
		voteList.add(new Candidate("Rahul", "RA102021", "BBA", 110));
		voteList.add(new Candidate("Pavithra", "RA222021", "BSC", 97));
		voteList.add(new Candidate("Jerom", "RA332021", "BBA", 78));
		return voteList;
	}
}