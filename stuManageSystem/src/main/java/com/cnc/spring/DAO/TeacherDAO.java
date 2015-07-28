package com.cnc.spring.DAO;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Course;
import com.cnc.spring.model.Teacher;

@Component
public class TeacherDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addTeacher(Teacher teacher) {
		getSession().flush();
		getSession().save(teacher);
	}
	
	public void deleteTeacher(String teacher_id) {
		getSession().flush();
		Teacher teacher = getTeacher(teacher_id);
		getSession().delete(teacher);
	}
	
	public Teacher getTeacher(String teacher_id) {
		return (Teacher)getSession().get(Teacher.class, teacher_id);
	}
	
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = (List<Teacher>)getSession().createQuery("from Teacher").list();
		return teachers;
	}
	
	public void updateTeacher(Teacher teacher) {
		Teacher t = getTeacher(teacher.getId());
		t.setName(teacher.getName());
		t.setPassword(teacher.getPassword());
		getSession().update(t);
	}
	
	public Set<Course> getCourses(String teacher_id) {
		Teacher teacher = getTeacher(teacher_id);
		return teacher.getCourses();
	}
}
