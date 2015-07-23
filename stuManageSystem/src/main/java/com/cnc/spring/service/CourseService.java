package com.cnc.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.CourseDAO;
import com.cnc.spring.DAO.TeacherDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Teacher;

/*
 * 课程的CRUD
 * 同时定义了一些课程的特殊方法，展示所有课程
 * 通过教工号查找所有课程
 * 通过学号查找所有课程
 */
@Component
public class CourseService {
	@Resource
	private CourseDAO courseDAO;
	@Resource
	private TeacherDAO teacherDAO;
	
	@Transactional
	public void addCourse(String teacher_id, Course course) {
		Teacher teacher = teacherDAO.getTeacher(teacher_id);
		course.setTeacher(teacher);
		courseDAO.addCourse(course);
	}
	@Transactional
	public void updateCourse(Course course) {
		courseDAO.updateCourse(course);
	}
	@Transactional
	public void deleteCourse(int course_id) {
		courseDAO.deleteCourse(course_id);
	}
	@Transactional
	public Course getCourse(int id) {
		return courseDAO.getCourse(id);
	}

	
	@Transactional
	public List<Course> listCourses() {
		return courseDAO.getCourses();
	}
	
	@Transactional
	public List<Course> listCourses(String teacher_id) {
		return courseDAO.getCourses(teacher_id);
	}
	
	
}
