package com.cnc.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnc.spring.DAO.CourseDAO;
import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.StudentDAO;

//Ñ§ÉúÑ¡¿Î
@Component
public class CourseSelectService {
	@Resource
	private StudentDAO studentDAO;
	@Resource
	private CourseDAO courseDAO;
	@Resource
	private ScoreDAO scoreDAO;
	
	
	public void courseSelection(String student_id, int course_id) {
		scoreDAO.saveScoreItem(studentDAO.getStudent(student_id), courseDAO.getCourse(course_id));
	}
	
	public void courseDeletion(String student_id, int course_id) {
		scoreDAO.deleteScore(student_id, course_id);
	}
}
