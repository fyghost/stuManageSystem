package com.cnc.spring.DAO;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.Student;

@Component
public class ScoreDAO {
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void saveScoreItem(Student student, Course course) {
		Score score = new Score();
		score.setCourse(course);
		student.getScores().add(score);
		score.setStudent(student);
		getSession().save(score);
	}
	
	public Score getScore(String studentId, int courseId) {
		Student student = (Student)getSession().get(Student.class, studentId);
		for(Score sc: student.getScores()) {
			if(sc.getCourse().getId() == courseId)
				return sc;
		}
		return null;
	}
	
	public void updateScore(String student_id, int course_id, double score) {
		Score sc = getScore(student_id, course_id);
		String hql = "update Score sc set sc.score=" + score +" where sc.id=" + sc.getId();
		getSession().createQuery(hql).executeUpdate();
	}
	
	public void deleteScore(String student_id, int course_id) {
		Score score = getScore(student_id, course_id);
		String hql = "delete Score sc where sc.id=" + score.getId();
		getSession().createQuery(hql).executeUpdate();
	}
	
	public Map<Course, Double> studentScoreMap(String studentId) {
		Student student = (Student)getSession().get(Student.class, studentId);
		if(student == null)
			return null;
		Map<Course, Double> scoreTable = new HashMap<Course, Double>();
		for(Score sc:student.getScores()) {
			scoreTable.put(sc.getCourse(), sc.getScore());
		}
		return scoreTable;
	}

	public Map<Student, Double> courseScoreMap(int courseId) {
		Course course = (Course)getSession().get(Course.class, courseId);
		if(course == null)
			return null;
		Map<Student, Double> scoreTable = new HashMap<Student, Double>();
		for(Score sc:course.getScores()) {
			scoreTable.put(sc.getStudent(), sc.getScore());
		}
		return scoreTable;
	} 
}
