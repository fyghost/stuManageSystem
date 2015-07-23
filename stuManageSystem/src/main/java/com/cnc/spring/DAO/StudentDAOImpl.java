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
	public void saveStudent(Student student) {
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
		String id = student.getId();
		String name = student.getName();
		String password = student.getPassword();
		Query query = getSession().createQuery("update Student t set t.name=?, t.password=? where t.id=?");
		query.setString(0, name);
		query.setString(1, password);
		query.setString(2, id);
		query.executeUpdate();
	}
	
	@Override
	public List<Student> getStudents() {
		List<Student> students = (List<Student>)getSession().createQuery("from Student").list();
		return students;
	}
}
