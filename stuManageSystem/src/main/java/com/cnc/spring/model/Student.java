package com.cnc.spring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="student")
@JsonIgnoreProperties (value={"scores", "password"})  
public class Student implements Serializable {
	private String id;
	private String name;
	private String password;
	private Set<Score> scores = new HashSet<Score>();
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@OneToMany(mappedBy="student",
			cascade={CascadeType.ALL},
			fetch=FetchType.LAZY
			)
	public Set<Score> getScores() {
		return scores;
	}
	public void setScores(Set<Score> scores) {
		this.scores = scores;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
