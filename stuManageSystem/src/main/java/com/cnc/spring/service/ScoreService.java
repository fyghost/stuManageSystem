package com.cnc.spring.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.cnc.spring.DAO.ScoreDAO;
import com.cnc.spring.model.Course;
import com.cnc.spring.model.Score;
import com.cnc.spring.model.Student;
//�����ϾͲ���ҪCRUD��ֻ��Ҫ��ʾ�͸��£������Ļ�����
@Component
public class ScoreService {
	@Resource
	private ScoreDAO scoreDAO;
	
	public Map<Course, Double> showStudentScores(String studentId) {
		return scoreDAO.studentScoreMap(studentId);
	}
	
	public Map<Student, Double> showCourseScores(int courseId) {
		return scoreDAO.courseScoreMap(courseId);
	}
	
	public int updateScore(String student_id, int course_id, double score) {
		Score sc = scoreDAO.getScore(student_id, course_id);
		//�����ѯ�ķ��������ڣ����������ֻ�е������student��course��id��Ӧ���ϵ�ʱ��Ż���֣�һ����˵�������
		if(sc == null)
			return -1;
		scoreDAO.updateScore(student_id, course_id, score);
		return 0;
	}
}
