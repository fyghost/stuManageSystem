package com.cnc.spring.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnc.spring.model.Student;
import com.cnc.spring.service.StudentService;

@Controller
public class AdminController {
	@Resource
	private StudentService studentService;
	
	@RequestMapping(value="student/add", method=RequestMethod.POST)
	public @ResponseBody String addStudent(@RequestParam("id") String id, @RequestParam String name) {
//		Student student = new Student(id, name);
//		student.setPassword(id);
//		studentService.addStudent(student);
		System.out.println(id + ":" + name); 
		String message = "student added";
		return message;
	}
}
