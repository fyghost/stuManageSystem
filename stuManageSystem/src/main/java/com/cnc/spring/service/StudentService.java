package com.cnc.spring.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.model.Student;

//ѧ����CRUD
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
			return "���û��Ѿ����ڣ���";
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		student.setPassword(id);
		student.setImg("default.jpg");
		studentDAO.addStudent(student);
		System.out.println(id + ":" + name); 
		return "��ӳɹ�";
	}
	
	@Transactional
	public String deleteStudent(String student_id) {
		Student student = studentDAO.getStudent(student_id);
		if(student == null) 
			return "�����Ҳ�����ѧ��";
		studentDAO.deleteStudent(student_id);
		return "ɾ���ɹ�";
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
		    file.getFileItem().write(file2); //���ϴ����ļ�д���½����ļ���
		} catch (Exception e) {
		    e.printStackTrace();
		    return "ͼƬ�ϴ�ʧ��";
		} 
		studentDAO.updateStudent(student);
		return "�ϴ��ɹ�";
	}
	
	@Transactional
	public void changePass(String student_id, String passwordNew) {
		Student student = studentDAO.getStudent(student_id);
		student.setPassword(passwordNew);
		studentDAO.updateStudent(student);
	}
}
