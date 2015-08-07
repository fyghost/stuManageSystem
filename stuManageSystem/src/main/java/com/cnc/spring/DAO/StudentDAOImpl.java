package com.cnc.spring.DAO;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Student;

@Component("studentDAO")
public class StudentDAOImpl implements StudentDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addStudent(Student student) {
		getSession().save(student);
	}

	@Override
	public void deleteStudent(String id) {
		Student student = getStudent(id);
		if(student != null) {
			String hql = "delete Student s where s.id=" + id;
			getSession().createQuery(hql).executeUpdate();
		}
	}

	@Override
	public Student getStudent(String id) {
		return (Student)getSession().get(Student.class, id);
	}

	@Override
	public void updateStudent(Student student) {
		Student s = getStudent(student.getId());
		s.setName(student.getName());
		s.setPassword(s.getPassword());
		if(student.getImg() != null) {
			s.setImg(student.getImg());
		}
		getSession().update(s);
	}
	
	@Override
	public List<Student> getStudents() {
		List<Student> students = (List<Student>)getSession().createQuery("from Student").list();
		return students;
	}
}
