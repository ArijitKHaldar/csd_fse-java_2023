package com.service;
import java.util.*;

import org.springframework.stereotype.Service;

import com.model.Course;

@Service
public class CourseService {
	ArrayList<Course> al=new ArrayList<Course>();
	
	
	public CourseService() {
		Course c1=new Course(101,"Frameworks",15000);
		Course c2=new Course(102,"Java", 10000);
		al.add(c1);
		al.add(c2);
		
	}
	
	public Course searchCourse(int courseId)
	{
		Course c=null;
		for(Course cObj: al)
		{
			if(cObj.getCourseId()==courseId)
			{
				c=cObj;
				break;
			}
		}
		return c;
	}

	public ArrayList<Course> getAl() {
		return al;
	}

	public void setAl(ArrayList<Course> al) {
		this.al = al;
	}

}
