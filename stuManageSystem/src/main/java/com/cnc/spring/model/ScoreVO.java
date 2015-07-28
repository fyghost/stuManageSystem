package com.cnc.spring.model;

/*
 * 创建这个类的主要目的是为了方便接收Json数据
 */
public class ScoreVO {
	private int score_id;
	private double score;
	
	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
}
