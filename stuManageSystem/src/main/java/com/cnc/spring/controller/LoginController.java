package com.cnc.spring.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cnc.spring.model.Admin;
import com.cnc.spring.model.Student;
import com.cnc.spring.model.Teacher;
import com.cnc.spring.service.AdminService;
import com.cnc.spring.service.StudentService;
import com.cnc.spring.service.TeacherService;

/*
 * 登录管理，根据不同的用户转到不同的页面
 * 老师，学生和管理员
 */
@Controller
public class LoginController {
	@Resource
	private StudentService studentService;
	@Resource
	private AdminService adminService;
	@Resource
	private TeacherService teacherService;
	
	private static final Logger logger = LoggerFactory.getLogger(GradeController.class);
	
	@RequestMapping(value="login")
	public String login() {
		logger.info("Login page");
		return "login";
	}
	
	@RequestMapping(value="user/login", method=RequestMethod.POST, params="user=student")
	public ModelAndView studentLogin(@RequestParam("id") String id, @RequestParam("password")String password) {
		logger.info("Login In...");
		System.out.println(id + ":" + password);
		Student student = new Student();
		student = studentService.getStudent(id);
		if(student == null) {
			return new ModelAndView("login").addObject("message", "此学号不存在！");
		} else if(!student.getPassword().equals(password)) {
			return new ModelAndView("login").addObject("message", "密码错误!");
		} else {
			logger.info("login success");
			ModelAndView mav = new ModelAndView("studentView");
			mav.addObject("student", student);
			return mav;
		}
	}
	
	@RequestMapping(value="user/login", method=RequestMethod.POST, params="user=admin")
	public ModelAndView adminLogin(@RequestParam("id") String username, @RequestParam("password")String password) {
		logger.info("Login In...");
		System.out.println(username + ":" + password);
		Admin admin = adminService.getAdmin(username);
		if(admin == null) {
			return new ModelAndView("login").addObject("message", "此管理员不存在！");
		} else if(!admin.getPassword().equals(password)) {
			return new ModelAndView("login").addObject("message", "密码错误！");
		}
		else {
			logger.info("login success");
			ModelAndView mav = new ModelAndView("adminView");
			return mav;
		}
	}
	@RequestMapping(value="user/login", method=RequestMethod.POST, params="user=teacher")
	public ModelAndView teacherLogin(@RequestParam("id") String username, @RequestParam("password")String password) {
		logger.info("Login In...");
		System.out.println(username + ":" + password);
		Teacher teacher = teacherService.getTeacher(username);
		if(teacher == null) {
			return new ModelAndView("login").addObject("message", "此教师号不存在！");
		} else if(!teacher.getPassword().equals(password)) {
			return new ModelAndView("login").addObject("message", "密码错误！");
		}
		else {
			logger.info("login success");
			ModelAndView mav = new ModelAndView("teacherView");
			mav.addObject("teacher", teacher);
			return mav;
		}
	}
}
