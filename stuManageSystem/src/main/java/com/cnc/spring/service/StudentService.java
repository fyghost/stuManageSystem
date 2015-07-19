package com.cnc.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.model.Student;

//Ñ§ÉúµÄCRUD
@Component
public class StudentService {
	@Resource
	private StudentDAO studentDAO;
	
	public Student getStudent(String id) {
		return studentDAO.getStudent(id);
	}
	
	public void addStudent(Student student) {
		studentDAO.saveStudent(student);
	}
	
	public void deleteStudent(String id) {
		studentDAO.deleteStudent(id);
	}
	
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
}
