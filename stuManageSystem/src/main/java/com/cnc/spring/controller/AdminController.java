package com.cnc.spring.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnc.spring.model.Student;
import com.cnc.spring.model.Teacher;
import com.cnc.spring.service.StudentService;
import com.cnc.spring.service.TeacherService;
import com.cnc.spring.validation.Login;
import com.cnc.spring.validation.ResultTypeEnum;

/*
 * Admin实现管理员的基本功能，增加删除学生或老师
 */
@Controller
public class AdminController {
	@Resource
	private StudentService studentService;
	@Resource
	private TeacherService teacherService;
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="user/add", params="user=student", method=RequestMethod.POST, produces="text/htm;charset=UTF-8")
	public @ResponseBody String addStudent(@RequestParam("id") String id, @RequestParam("name") String name) {
		logger.info("adding a new student");
		
		return studentService.addStudent(id, name);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="user/add", params={"user=teacher"}, method=RequestMethod.POST,  produces = "text/html;charset=UTF-8")
	public @ResponseBody String addTeacher(@RequestParam("id") String id, @RequestParam("name") String name) {
		logger.info("adding a new teacher");
		
		return teacherService.addTeacher(id, name);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/list")
	public @ResponseBody List<Student> listStudents() {
		logger.info("listing students");
		List<Student> students = studentService.getStudents();
		for(Student s: students)
			System.out.println(s.getId() + ":" + s.getName() + ":" + s.getPassword());
		return students;
//		return students.get(0);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("teacher/list")
	public @ResponseBody List<Teacher> listTeachers() {
		logger.info("listing teachers");
		List<Teacher> teachers = teacherService.getTeachers();
		for(Teacher t: teachers)
			System.out.println(t.getId() + ":" + t.getName() + ":" + t.getPassword());
		return teachers;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("teacher/delete/{id}")
	public @ResponseBody String deleteTeacher(@PathVariable("id") String teacher_id) {
		logger.info("deleting teacher");
		return teacherService.deleteTeacher(teacher_id);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="student/delete/{id}", produces="text/html;charset=UTF-8")
	public @ResponseBody String deleteStudent(@PathVariable("id") String student_id) {
		logger.info("deleting teacher");
		return studentService.deleteStudent(student_id);
	}
}
