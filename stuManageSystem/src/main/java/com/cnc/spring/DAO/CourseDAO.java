package com.cnc.spring.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
		c.setPeriod(course.getPeriod());
		c.setWeekday(course.getWeekday());
		getSession().update(c);
	}
	
	public boolean hasCourseOfTeacher(String id, String weekday, String period) {
		SQLQuery q = getSession().createSQLQuery("select * from course where teacher_id=? and weekday=? and period=?");
		q.setString(0, id);
		q.setString(1, weekday);
		q.setString(2, period);
		List<Course> courses = q.list();
		if(courses.size() == 0)
			return false;
		return true;
	}
	
	public boolean hasCourseOfStudent(String id, String weekday, String period) {
		String hql = "select * from course where id in (select course_id from score where student_id=?) and weekday=? and period=?";
		SQLQuery q = getSession().createSQLQuery(hql);
		q.setString(0, id);
		q.setString(1, weekday);
		q.setString(2, period);
		List<Course> courses = q.list();
		if(courses.size() == 0)
			return false;
		return true;
	}
	
	public List<Course> getCourses() {
		return getSession().createQuery("from Course").list();
	}
	
	public List<Course> getCourses(String teacher_id) {
		return getSession().createQuery("from Course where teacher_id=" + teacher_id).list();
	}
	
	public List<Course> getCourse(String weekday, String period) {
		Query q = getSession().createQuery("from Course where weekday=? and period=?");
		q.setString(0, weekday);
		q.setString(1, period);
		return q.list();
	}
}
