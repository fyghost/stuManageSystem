package com.cnc.spring.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cnc.spring.model.Student;
import com.cnc.spring.service.StudentService;

@Controller
public class GradeController {
	@Resource
	private StudentService studentService;
	
	private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
	
	@RequestMapping(value="/student/gradeShow",method=RequestMethod.POST)
	public ModelAndView gradeReq(String id, String name) {
		logger.info("Request for the grade of student " + name);
		Student student = studentService.getStudent(id);
		if(student != null && name.equals(student.getName())){
			ModelAndView mav = new ModelAndView("gradeShow");
			mav.addObject("student", student);
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("gradeReq");
			String message = "姓名或学号错误，请重新输入";
			mav.addObject("message", message);
			return mav;
		}
		
	}
	
	@RequestMapping(value="/student/gradeReq", method=RequestMethod.GET) 
	public ModelAndView gradeShow() {
		logger.info("Show the requesting page");
		return new ModelAndView("gradeReq");
	}
	
	@RequestMapping(value="add")
	public String add() {
		return "addGrade";
	}
	
	@RequestMapping(value="/grade/add", method=RequestMethod.POST) 
	public ModelAndView gradeAdd(String name, String password) {
		Student student = new Student();
		student.setName(name);
		System.out.println(student.getName());
		studentService.addStudent(student);
		ModelAndView mav = new ModelAndView("home");
		String message = "A Student was successfully added";
		mav.addObject("message", message);
		return mav;
	}
}
