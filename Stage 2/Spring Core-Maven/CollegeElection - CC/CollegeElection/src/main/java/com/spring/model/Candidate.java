package com.spring.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//POJO class
//Use appropriate annotation
@Component
public class Candidate {
	private static int totalVotes;
	public static int getTotalVotes() {
		return totalVotes;
	}
	private String name;
	private String candidateId;
	private String department;

	private int numberOfVotes;

	public Candidate() {
	}

	public Candidate(String name, String candidateId, String department, int numberOfVotes) {
		super();
		this.name = name;
		this.candidateId = candidateId;
		this.department = department;
		this.numberOfVotes = numberOfVotes;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public String getDepartment() {
		return department;
	}

	public String getName() {
		return name;
	}

	public int getNumberOfVotes() {
		return numberOfVotes;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumberOfVotes(int numberOfVotes) {
		this.numberOfVotes = numberOfVotes;
	}

	@Value("${totalVotes}")
	public void setTotalVotes(int totalVotes) {
		Candidate.totalVotes = totalVotes;
	}

	@Override
	public String toString() {
		return "Candidate " + this.name + " got " + this.numberOfVotes + " votes";
	}
}