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
	public String selectCourse(String student_id, int course_id) {
		Course c = courseDAO.getCourse(course_id);
		Student s = studentDAO.getStudent(student_id);
		if(courseDAO.hasCourseOfStudent(student_id, c.getWeekday(), c.getPeriod()))
			return "该时段已经有课程";
		scoreDAO.saveScoreItem(s, c);
		return "选课成功";
	}
	
	@Transactional
	public void deleteCourseItem(String student_id, int course_id) {
		scoreDAO.deleteScore(student_id, course_id);
	}
	
	@Transactional
	public List<Course> listCourses() {
		return courseDAO.getCourses();
	}
	
	@Transactional
	public List<Course> listCourseNotSelected(String student_id) {
		List<Course> courses = listCourses();
		for(Course c: courses) {
			if(!scoreDAO.isSelected(student_id, c.getId()))//区分开选上和未选上的课程
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
