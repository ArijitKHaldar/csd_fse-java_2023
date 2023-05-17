package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exception.CourseNotFoundException;
import com.model.Course;
import com.service.CourseService;

@RestController
@RequestMapping("/cms")
public class CourseController {

	// include the appropraiate annotation
	@Autowired
	private CourseService courseService;

//include the appropriate annotation
	@GetMapping("/find/{courseId}")
	public Course findCourse(@PathVariable("courseId") Integer courseId) {

//include the appropriate business logic
		Course courseObj = courseService.searchCourse(courseId);
		if (courseObj == null) {
			throw new CourseNotFoundException("No such course id");
		} else {
			return courseObj;
		}

	}
}