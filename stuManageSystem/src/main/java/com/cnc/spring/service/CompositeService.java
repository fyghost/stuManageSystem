package com.cnc.spring.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.CourseDAO;
import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.StudentDAO;
import com.cnc.spring.DAO.TeacherDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Student;

/*
 * ���service������ѧ��ѡ�Σ�ɾ���γ�
 * ����ѧ�Ų���չʾ�γ̣����ݽ̹���չʾ���пγ̵�����
 * �Լ����ݿγ̺Ų��ҵ����е�ѧ��
 */
@Component
public class CompositeService {
	@Resource
	private StudentDAO studentDAO;
	@Resource
	private CourseDAO courseDAO;
	@Resource
	private ScoreDAO scoreDAO;
	
	@Transactional
	public void courseSelection(String student_id, int course_id) {
		scoreDAO.saveScoreItem(studentDAO.getStudent(student_id), courseDAO.getCourse(course_id));
	}
	
	@Transactional
	public void courseDeletion(String student_id, int course_id) {
		scoreDAO.deleteScore(student_id, course_id);
	}
	
	@Transactional
	public List<Course> listCoursesByStu(String student_id) {
		return scoreDAO.listCourses(student_id);
	}
	
	@Transactional
	public List<Student> listStudents(int course_id) {
		return scoreDAO.listStudents(course_id);
	}
}
