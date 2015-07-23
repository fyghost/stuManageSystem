package com.cnc.spring.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.CourseDAO;
import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.Student;

/*
 * 这个service定义了学生选课，删除课程
 * 根据学号查找展示课程，根据教工号展示所有课程等内容
 * 以及根据课程号查找到所有的学生
 */
@Component
public class CompositeService {
	@Resource
	private StudentDAO studentDAO;
	@Resource
	private CourseDAO courseDAO;
	@Resource
	private ScoreDAO scoreDAO;
	
	@Transactional
	public void selectCourse(String student_id, int course_id) {
		Course c = courseDAO.getCourse(course_id);
		Student s = studentDAO.getStudent(student_id);
		scoreDAO.saveScoreItem(s, c);
	}
	
	@Transactional
	public void deleteCourseItem(String student_id, int course_id) {
		scoreDAO.deleteScore(student_id, course_id);
	}
	
	@Transactional
	public List<Course> listCoursesByStu(String student_id) {
		List<Score> scores = scoreDAO.getScores(student_id);
		List<Course> courses = new ArrayList<Course>();
		for(Score s: scores) {
			courses.add(s.getCourse());
		}
		return courses;
	}
	
	@Transactional
	public List<Course> listCourses() {
		return courseDAO.getCourses();
	}
	
	@Transactional
	public List<Student> listStudentsByCourse(int course_id) {
		List<Score> scores = scoreDAO.getScores(course_id);
		List<Student> students = new ArrayList<Student>();
		for(Score s: scores) {
			students.add(s.getStudent());
		}
		return students;
	}
	
	@Transactional
	public List<Course> listCourseNotSelected(String student_id) {
		List<Course> courses = listCourses();
		for(Course c: courses) {
			if(!scoreDAO.isSelected(student_id, c.getId()))
				c.setSelected((short)0);
		}
		return courses;
	}
	
	@Transactional
	public List<Score> listScores(String student_id) {
		return scoreDAO.getScores(student_id);
	}
	
	@Transactional
	public List<Score> listScores(int course_id) {
		return scoreDAO.getScores(course_id);
	}
}
