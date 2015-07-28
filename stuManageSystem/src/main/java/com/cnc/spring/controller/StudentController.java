package com.cnc.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.Student;
import com.cnc.spring.service.CompositeService;
import com.cnc.spring.service.StudentService;
import com.cnc.spring.validation.Login;
import com.cnc.spring.validation.ResultTypeEnum;

@Controller
public class StudentController {
	@Resource
	private CompositeService compositeService;
	@Resource
	private StudentService studentService;
	
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/courses/{student_id}")
	public @ResponseBody List<Course> listCourses(@PathVariable("student_id")String student_id) {
		logger.info("Listing Course...");
		return compositeService.listCourseNotSelected(student_id);
		
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/course/{id}")
	public @ResponseBody List<Score> listCoursesById(@PathVariable("id")String id) {
		logger.info("Listing Score And Course");
		return compositeService.listScores(id);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("select/course/{student_id}/{course_id}")
	public @ResponseBody String addCourse(@PathVariable("student_id")String student_id, @PathVariable("course_id")int course_id) {
		logger.info("Adding Course");
		compositeService.selectCourse(student_id, course_id);
		String message = "success";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("delete/course/{student_id}/{course_id}")
	public @ResponseBody String deleteCourse(@PathVariable("student_id")String student_id, @PathVariable("course_id")int course_id) {
		logger.info("Deleting Course....");
		compositeService.deleteCourseItem(student_id, course_id);
		return "success";
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="student/password/{student_id}")
	public @ResponseBody int changePass(@PathVariable("student_id") String student_id, String passwordNew, String passwordConfirm) {
		if(!passwordNew.equals(passwordConfirm))
			return 0;
		Student student = studentService.getStudent(student_id);
		student.setPassword(passwordNew);
		studentService.updateStudent(student);
		return 1;
	}
}
