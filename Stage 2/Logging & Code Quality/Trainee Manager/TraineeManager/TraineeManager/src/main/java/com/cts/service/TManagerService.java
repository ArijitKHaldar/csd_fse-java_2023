package com.cts.service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.TraineeManagerApplication;
import com.cts.entity.Trainee;
import com.cts.exception.InvalidTraineeDataException;
import com.cts.exception.InvalidTraineeoperationException;
import com.cts.repository.ITManagerRepository;

@Service("trainee")

public class TManagerService {
	Logger logger = LoggerFactory.getLogger(TManagerService.class);
	@Autowired
	private ITManagerRepository repo;

	public void createTrainee(Trainee t) throws InvalidTraineeDataException, InvalidTraineeoperationException {
		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern pattern = Pattern.compile(regex);
		String email = t.getEmail();
		Matcher matcher = pattern.matcher(email);
		boolean e=matcher.matches();
		if(!matcher.matches()) {
			logger.error("Wrong email format");
			throw new InvalidTraineeDataException("Incorrect email");
		}
		
		String s = t.getName();
		boolean b = isAlpha(s);
		if(b==false) {
			logger.error("Name field can have only letters");
			throw new InvalidTraineeDataException("Incorrect name");
		}
		
		int l = Integer.valueOf(t.getEmpId());
		if(l<6) {
			logger.error("Wrong empid");
			throw new InvalidTraineeDataException("Incorrect empid");
		}
		
		
		String code=t.getCohortcode();
		boolean ccode=Pattern.matches("[A-Z]{3}[0-9]{2}[A-Z]{2}[0-9]{2}",code );
		if(ccode==false) {
			logger.error("Wrong cohort code format");
			throw new InvalidTraineeDataException("Incorrect cohortcode");
		}		
		
		Optional<Trainee> op = repo.findById(t.getEmpId());
		if (op.isPresent()) {
			System.out.println("already");
			throw new InvalidTraineeoperationException("Trainee cannot be added in more than one cohort");
		}
		
		else {
			if (l==6 && b && e && ccode) {
				repo.saveAndFlush(t);
				System.out.println("\nCreated");
			}
			else {
				System.out.println("Error in registering new trainee");
			}
		}
		
	}
	
	public static boolean isAlpha(String s) {
		if (s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!(c >= 'A' && c <= 'Z') && !(c >= 'a' && c <= 'z')) {
				return false;
			}
		}
		return true;
	}

	
	
	
	public List<Trainee> getAllTrainee() {
		return repo.findAll();
	}

	public Trainee getTraineeById(int tid) {
		Optional<Trainee> op = repo.findById(tid);
		if (op.isPresent())
			return op.get();
		else
			return null;
	}

	public String removeTrainee(int tid) {
		Optional<Trainee> op = repo.findById(tid);
		if (op.isPresent()) {
			repo.delete(op.get());
			return "Trainee Deleted!!!!";
		} 
		else {
			return "Trainee Not Available";
		}
	}

	public Trainee findByTName(String name) {
		return repo.findByName(name);
	}

}
