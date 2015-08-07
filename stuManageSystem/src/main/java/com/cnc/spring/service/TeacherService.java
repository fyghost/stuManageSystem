package com.cnc.spring.service;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.TeacherDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Student;
import com.cnc.spring.model.Teacher;

@Component
public class TeacherService {
	@Resource
	private TeacherDAO teacherDAO;
	@Resource
	private ScoreDAO scoreDAO;
	
	private static final String path = "D:/apache-tomcat-7.0.55/webapps/resources/img/teacher";
	
	@Transactional
	public Teacher getTeacher(String id) {
		return teacherDAO.getTeacher(id);
	}
	
	@Transactional
	public String deleteTeacher(String teacher_id) {
		Teacher teacher = teacherDAO.getTeacher(teacher_id);
		if(teacher == null)
			return "����ʧ��";
		Set<Course> courses = teacherDAO.getCourses(teacher_id);
		for(Course c: courses) {
			scoreDAO.deleteScore(c.getId());
		}
		teacherDAO.deleteTeacher(teacher_id);
		return "ɾ���ɹ�";
	}
	
	@Transactional
	public void updateTeacher(Teacher teacher) {
		teacherDAO.updateTeacher(teacher);
	}
	
	@Transactional
	public String addTeacher(String id, String name) {
		if(teacherDAO.getTeacher(id) != null)
			return "���û��Ѿ����ڣ���";
		Teacher teacher = new Teacher();
		teacher.setId(id);
		teacher.setName(name);
		teacher.setPassword(id);
		teacher.setImg("default.jpg");
		teacherDAO.addTeacher(teacher);
		return "��ӳɹ�";
	}
	
	@Transactional
	public List<Teacher> getTeachers() {
		return teacherDAO.getTeachers();
	}
	
	@Transactional
	public String changePic(CommonsMultipartFile file, String type, String teacher_id) {
		String newFileName = "teacher" + teacher_id + type;
		String fileloc = "teacher/" + newFileName;
		Teacher teacher = teacherDAO.getTeacher(teacher_id);
		teacher.setImg(fileloc);
		File file2 = new File(path, newFileName);
		try {
		    file.getFileItem().write(file2); //���ϴ����ļ�д���½����ļ���
		} catch (Exception e) {
		    e.printStackTrace();
		    return "ͼƬ�ϴ�ʧ��";
		} 
		teacherDAO.updateTeacher(teacher);
		return "�ϴ��ɹ�";
	}
 }
