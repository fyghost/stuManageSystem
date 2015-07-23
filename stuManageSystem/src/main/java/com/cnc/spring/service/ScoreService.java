package com.cnc.spring.service;

import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.DAO.TeacherDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.Student;
/*
 * �����ϾͲ���ҪCRUD��ֻ��Ҫ��ѯ�͸��£�����ɾ���Ļ�������
 */
@Component
public class ScoreService {
	@Resource
	private ScoreDAO scoreDAO;
	
	@Transactional
	public Map<Course, Double> showStudentScores(String studentId) {
		return scoreDAO.studentScoreMap(studentId);
	}
	@Transactional
	public Map<Student, Double> showCourseScores(int courseId) {
		return scoreDAO.courseScoreMap(courseId);
	}
	@Transactional
	public int updateScore(String student_id, int course_id, double score) {
		Score sc = scoreDAO.getScore(student_id, course_id);
		//�����ѯ�ķ��������ڣ����������ֻ�е������student��course��id��Ӧ���ϵ�ʱ��Ż���֣�һ����˵�������
		if(sc == null)
			return -1;
		scoreDAO.updateScore(student_id, course_id, score);
		return 0;
	}
	
}
