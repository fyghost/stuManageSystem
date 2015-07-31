package com.cnc.spring.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.ScoreVO;
import com.cnc.spring.model.Student;
import com.cnc.spring.model.Teacher;
import com.cnc.spring.service.CompositeService;
import com.cnc.spring.service.CourseService;
import com.cnc.spring.service.ScoreService;
import com.cnc.spring.service.TeacherService;
import com.cnc.spring.validation.Login;
import com.cnc.spring.validation.ResultTypeEnum;

@Controller
public class TeacherController {
	@Resource
	private CourseService courseService;
	@Resource
	private ScoreService scoreService;
	@Resource
	private CompositeService compositeService;
	@Resource
	private TeacherService teacherService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);
	@Login(ResultTypeEnum.json)
	@RequestMapping("course/add/{id}")
	public @ResponseBody String addCourse(@PathVariable("id") String id, @RequestParam("course_name") String course_name) {
		logger.info("Adding courses");
		Course course = new Course();
		System.out.println(course_name);
		course.setName(course_name);
		courseService.addCourse(id, course);
		String message = "Course added";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("score/set/{id}")
	public @ResponseBody String setScore(String student_id, int course_id, double score) {
		logger.info("Setting the grades...");
		scoreService.updateScore(student_id, course_id, score);
		String message = "成绩已录入";
		return message;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("course/list/{id}")
	public @ResponseBody List<Course> listCourse(@PathVariable("id") String id) {
		logger.info("Listing the courses");
		return courseService.listCourses(id);
		
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping("student/list/{course_id}")
	public @ResponseBody List<Score> listStudent(@PathVariable("course_id") int course_id) {
		logger.info("Listing the students");
		return compositeService.listScores(course_id);
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="set/scores/{course_id}", method=RequestMethod.POST) 
	public @ResponseBody String setScores(@RequestBody List<ScoreVO> scores, @PathVariable("course_id") int course_id) {
		logger.info("setting scores");
		for(ScoreVO s : scores)
			scoreService.updateScore(s);
		return "success";
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="teacher/password/{teacher_id}")
	public @ResponseBody int changePass(@PathVariable("teacher_id") String teacher_id, String passwordNew, String passwordConfirm) {
		if(!passwordNew.equals(passwordConfirm))
			return 0;
		Teacher teacher = teacherService.getTeacher(teacher_id);
		teacher.setPassword(passwordNew);
		teacherService.updateTeacher(teacher);
		return 1;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="teacher/picture/{teacher_id}", produces="text/html;charset=utf-8")
	public @ResponseBody String changPic(@PathVariable("teacher_id")String teacher_id, @RequestParam("imgFile") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Uploading Teacher Images");
		if (!file.isEmpty()) {
			String path = request.getSession().getServletContext().getRealPath("/resources/img/teacher");  //获取本地存储路径
			System.out.println(path);
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			Teacher teacher = teacherService.getTeacher(teacher_id);

			String newFileName = "teacher" + teacher.getId() + fileType;
			String fileLocation = "/resources/img/teacher" + newFileName;
			File file2 = new File(path, newFileName); //新建一个文件
			try {
			    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return "fileLocation";
		}else{
			return "上传失败";
		}
	}
}
