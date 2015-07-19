package com.cnc.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnc.spring.DAO.CourseDAO;
import com.cnc.spring.model.Course;

//¿Î³ÌµÄCRUD
@Component
public class CourseService {
	@Resource
	private CourseDAO courseDAO;
	
	public void addCourse(Course course) {
		courseDAO.addCourse(course);
	}
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}
	
	public void deleteCourse(int id) {
		courseDAO.deleteCourse(id);
	}
	
	public Course getCourse(int id) {
		return courseDAO.getCourse(id);
	}
}
