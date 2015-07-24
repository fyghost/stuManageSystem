package com.cnc.spring.DAO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.ScoreVO;
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
		System.out.println(student.getName());
		System.out.println(course.getName());
		score.setCourse(course);
		
		student.getScores().add(score);
		score.setStudent(student);
		getSession().save(score);
	}
	
	public Score getScore(String student_id, int course_id) {
		Student student = (Student)getSession().get(Student.class, student_id);
		for(Score sc: student.getScores()) {
			if(sc.getCourse().getId() == course_id)
				return sc;
		}
		return null;
	}
	public Score getScore(int score_id) {
		System.out.println("break");
		return (Score)getSession().get(Score.class, score_id);
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
	
	public List<Score> getScores(int course_id) {
		return (List<Score>)getSession().createQuery("from Score where course_id=" + course_id).list();
	}
	
	public List<Score> getScores(String student_id) {
		return (List<Score>)getSession().createQuery("from Score where student_id=" + student_id).list();
	}
	
	public boolean isSelected(String student_id, int course_id) {
		Iterator it = getSession().createQuery("from Score where student_id=" + student_id + " and course_id=" + course_id).iterate();
		if(it.hasNext())
			return true;
		return false;
	}
	
	public void setScore(ScoreVO s) {
		Score score = getScore(s.getScore_id());
		
		score.setScore(s.getScore());
		getSession().update(score);
	}
}
