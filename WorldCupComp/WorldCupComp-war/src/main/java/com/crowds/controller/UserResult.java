package com.crowds.controller;

import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;


public class UserResult {

	public Logger			m_logger	= 	Logger.getLogger(UserResult.class.getName());
	
	private String	userName;
	private double	score;
	
	/**
	 * Default Constructor
	 */
	public UserResult() {
	}
	
	/**
	 * Overloaded Constructor 
	 */
	public UserResult(String userName, double score) {
		this.userName = userName;
		this.score = score;	
	}
	
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName( String p_userName ) {
		this.userName = p_userName;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore( double p_score ) {
		this.score = p_score;
	}
	
}
