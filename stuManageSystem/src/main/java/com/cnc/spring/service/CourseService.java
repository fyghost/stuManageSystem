package com.cnc.spring.service;

import java.util.List;
import java.util.Set;

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
	public String addCourse(String teacher_id, String weekday, String period, String course_name) {
		//List<Course> courses = courseDAO.getCourse(weekday, period);
		if(courseDAO.hasCourseOfTeacher(teacher_id, weekday, period)) {
			System.out.println("Wrong time Added");
			return "该时段已经有课程";
		}
		Course course = new Course();
		course.setName(course_name);
		course.setPeriod(period);
		course.setWeekday(weekday);
		Teacher teacher = teacherDAO.getTeacher(teacher_id);
		course.setTeacher(teacher);
		courseDAO.addCourse(course);
		return "添加成功";
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
	public List<Course> listCourses(String teacher_id) {
		return courseDAO.getCourses(teacher_id);
	}
	
}
