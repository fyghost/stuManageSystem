package com.cnc.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.model.Student;

//Ñ§ÉúµÄCRUD
@Component
public class StudentService {
	@Resource
	private StudentDAO studentDAO;
	
	@Transactional
	public Student getStudent(String id) {
		return studentDAO.getStudent(id);
	}
	@Transactional
	public void addStudent(Student student) {
		studentDAO.saveStudent(student);
	}
	
	@Transactional
	public void deleteStudent(String id) {
		studentDAO.deleteStudent(id);
	}
	
	@Transactional
	public void updateStudent(Student student) {
		studentDAO.updateStudent(student);
	}
	
	@Transactional
	public List<Student> getStudents() {
		return studentDAO.getStudents();
	}
}
