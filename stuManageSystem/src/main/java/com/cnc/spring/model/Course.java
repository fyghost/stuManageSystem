package com.cnc.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="course")
@JsonIgnoreProperties (value={"scores", "teacher"})  
public class Course implements Serializable {
	private int id;
	private String name;
	private Teacher teacher;
	private String teacher_name;
	private short selected = 1;
	private Set<Score> scores = new HashSet<Score>();
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="teacher_id")
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		setTeacher_name(teacher.getName());
		this.teacher = teacher;
	}
	
	@OneToMany(mappedBy="course",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	public Set<Score> getScores() {
		return scores;
	}
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	@Transient
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	@Transient
	public short getSelected() {
		return selected;
	}
	public void setSelected(short selected) {
		this.selected = selected;
	}
}
