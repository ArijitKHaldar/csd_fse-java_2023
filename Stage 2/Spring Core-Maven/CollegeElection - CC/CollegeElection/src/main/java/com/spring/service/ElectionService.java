package com.spring.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.dao.ElectionDAO;
import com.spring.model.Candidate;

//Use appropriate annotation
@Component
public class ElectionService {
	private ElectionDAO electionDAO;

	@Autowired
	public ElectionService(ElectionDAO electionDAO) {
		super();
		this.electionDAO = electionDAO;
	}

	public Candidate candidateWithMaximumVote() {
		return electionDAO.candidateWithMaximumVote();
	}

	public ElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public int getTotalVotes() {
		return electionDAO.getTotalVotes();
	}

	public ArrayList<Candidate> getVoteList() {
		return electionDAO.getVoteList();
	}

	public void setElectionDAO(ElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public int totalpolledVotes() {
		return electionDAO.totalpolledVotes();
	}

	public int unpolledVotes() {
		return electionDAO.unpolledVotes();
	}
}