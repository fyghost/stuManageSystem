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
 * 分数上就不需要CRUD，只需要查询和更新，增加删除的话会联动
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
		//如果查询的分数不存在，这种情况，只有当请求的student和course的id对应不上的时候才会出现，一般来说不会出错
		if(sc == null)
			return -1;
		scoreDAO.updateScore(student_id, course_id, score);
		return 0;
	}
	
}
