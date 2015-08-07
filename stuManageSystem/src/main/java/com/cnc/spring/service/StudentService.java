package com.cnc.spring.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.model.Student;

//学生的CRUD
@Component
public class StudentService {
	@Resource
	private StudentDAO studentDAO;
	
	private static final String path = "D:/apache-tomcat-7.0.55/webapps/resources/img/student" ;
	
	@Transactional
	public Student getStudent(String id) {
		return studentDAO.getStudent(id);
	}
	@Transactional
	public String addStudent(String id, String name) {
		if(studentDAO.getStudent(id) != null)
			return "该用户已经存在！！";
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setPassword(id);
		student.setImg("default.jpg");
		studentDAO.addStudent(student);
		System.out.println(id + ":" + name); 
		return "添加成功";
	}
	
	@Transactional
	public String deleteStudent(String student_id) {
		Student student = studentDAO.getStudent(student_id);
		if(student == null) 
			return "错误，找不到该学生";
		studentDAO.deleteStudent(student_id);
		return "删除成功";
	}
	
	@Transactional
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
	
	@Transactional
	public List<Student> getStudents() {
		return studentDAO.getStudents();
	}
	
	@Transactional
	public String changePic(CommonsMultipartFile file, String type, String student_id) {
		String newFileName = "student" + student_id + type;
		String fileloc = "student/" + newFileName;
		Student student = studentDAO.getStudent(student_id);
		student.setImg(fileloc);
		File file2 = new File(path, newFileName);
		try {
		    file.getFileItem().write(file2); //将上传的文件写入新建的文件中
		} catch (Exception e) {
		    e.printStackTrace();
		    return "图片上传失败";
		} 
		studentDAO.updateStudent(student);
		return "上传成功";
	}
	
	@Transactional
	public void changePass(String student_id, String passwordNew) {
		Student student = studentDAO.getStudent(student_id);
		student.setPassword(passwordNew);
		studentDAO.updateStudent(student);
	}
}
