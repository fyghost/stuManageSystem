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
	@RequestMapping(value="user/add", params="user=student", method=RequestMethod.POST)
	public @ResponseBody String addStudent(@RequestParam("id") String id, @RequestParam("name") String name) {
		logger.info("adding a new student");
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setPassword(id);
		studentService.addStudent(student);
		System.out.println(id + ":" + name); 
		String message = "student added";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="user/add", params="user=teacher", method=RequestMethod.POST)
	public @ResponseBody String addTeacher(@RequestParam("id") String id, @RequestParam("name") String name) {
		logger.info("adding a new teacher");
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setName(name);
		teacher.setPassword(id);
		teacherService.addTeacher(teacher);
		System.out.println(id + ":" + name); 
		String message = "teacher added";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/list")
	public @ResponseBody List<Student> listStudents() {
		logger.info("listing students");
		List<Student> students = studentService.getStudents();
//		for(Student s: students)
//			System.out.println(s.getId() + ":" + s.getName() + ":" + s.getPassword());
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
		Teacher teacher = teacherService.getTeacher(teacher_id);
		if(teacher == null) 
			return "No such teacher";
		teacherService.deleteTeacher(teacher_id);
		String message = "Teacher deleted";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/delete/{id}")
	public @ResponseBody String deleteStudent(@PathVariable("id") String student_id) {
		logger.info("deleting teacher");
		Student student = studentService.getStudent(student_id);
		if(student == null) 
			return "No such student";
		studentService.deleteStudent(student_id);
		String message = "Student deleted";
		return message;
	}
}
