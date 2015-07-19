package com.cnc.spring.DAO;

import com.cnc.spring.model.Student;

public interface StudentDAO {
	void saveStudent(Student student);
	void deleteStudent(String id);
	Student getStudent(String id);
	void updateStudent(Student student);
}
