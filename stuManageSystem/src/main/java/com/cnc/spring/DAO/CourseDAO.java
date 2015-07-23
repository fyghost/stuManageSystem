package com.cnc.spring.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Course;

@Component
public class CourseDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void addCourse(Course course) {
		getSession().save(course);
	}
	
	public Course getCourse(int id) {
		return (Course)getSession().get(Course.class, id);
	}
	
	public void deleteCourse(int id) {
		Course course = getCourse(id);
		if(course != null) {
			String hql = "delete Course c where c.id=" + course.getId();
			getSession().createQuery(hql).executeUpdate();
		}
	}
	
	public void updateCourse(Course course) {
		Course c = getCourse(course.getId());
		c.setName(course.getName());
		c.setTeacher(course.getTeacher());
		getSession().update(c);
	}
	
	public List<Course> getCourses() {
		return getSession().createQuery("from Course").list();
	}
	
	public List<Course> getCourses(String teacher_id) {
		return getSession().createQuery("from Course where teacher_id=" + teacher_id).list();
	}
}
