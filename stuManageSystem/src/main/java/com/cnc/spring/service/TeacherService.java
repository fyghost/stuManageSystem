package com.cnc.spring.service;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.TeacherDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Teacher;

@Component
public class TeacherService {
	@Resource
	private TeacherDAO teacherDAO;
	@Resource
	private ScoreDAO scoreDAO;
	
	@Transactional
	public Teacher getTeacher(String id) {
		return teacherDAO.getTeacher(id);
	}
	
	@Transactional
	public void deleteTeacher(String teacher_id) {
		Set<Course> courses = teacherDAO.getCourses(teacher_id);
		for(Course c: courses) {
			scoreDAO.deleteScore(c.getId());
		}
		teacherDAO.deleteTeacher(teacher_id);
	}
	
	@Transactional
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}
	
	@Transactional
	public void addTeacher(Teacher teacher) {
		teacherDAO.addTeacher(teacher);
	}
	
	@Transactional
	public List<Teacher> getTeachers() {
		return teacherDAO.getTeachers();
	}
	
 }
