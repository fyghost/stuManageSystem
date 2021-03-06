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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	public @ResponseBody String selectCourse(@PathVariable("student_id")String student_id, @PathVariable("course_id")int course_id) {
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
		else if(passwordNew == null || passwordNew.length() == 0)
			return 0;
		studentService.changePass(student_id, passwordNew);
		return 1;
	}
	
	@Login(ResultTypeEnum.json)
	@RequestMapping(value="student/picture/{student_id}", produces="text/html;charset=utf-8")
	public @ResponseBody String changPic(@PathVariable("student_id")String student_id, @RequestParam("imgFile") CommonsMultipartFile file,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("Uploading Images");
		if (!file.isEmpty()) {
			//String path = request.getSession().getServletContext().getRealPath("/resources/img/student");  //获取本地存储路径
			//String path = "D:/apache-tomcat-7.0.55/webapps/resources/img/student";
			String fileName = file.getOriginalFilename();
			String fileType = fileName.substring(fileName.lastIndexOf("."));
			boolean legal = fileType.equals("jpg") || fileType.equals("bmp") || fileType.equals("jpeg") || fileType.equals("gif");
			if(! legal)
				return "请上传正确的图片格式";
//			Student student = studentService.getStudent(student_id);
//			String newFileName = "student" + student.getId() + fileType;
//			String fileLocation = "student/" + newFileName;
//			student.setImg(fileLocation);
//			File file2 = new File(path, newFileName); //新建一个文件
//			try {
//			    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
//			} catch (Exception e) {
//			    e.printStackTrace();
//			}
//			studentService.updateStudent(student);
			return studentService.changePic(file, fileType, student_id);
		}else{
			return "请选择图片";
		}
	}
}
