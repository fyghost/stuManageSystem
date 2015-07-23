package com.cnc.spring.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="score")
@JsonIgnoreProperties(value={"student", "course"})
public class Score implements Serializable {
	int id;
	private Double score;
	private Student student;
	private Course course;
	private int course_id;
	private String course_name;
	private String student_name;
	private String teacher_name;
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student_name = student.getName();
		this.student = student;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course_name = course.getName();
		this.course_id = course.getId();
		this.setTeacher_name(course.getTeacher_name());
		this.course = course;
	}
	
	@Transient
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	
	@Transient
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	@Transient
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	@Transient
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	
	
}
