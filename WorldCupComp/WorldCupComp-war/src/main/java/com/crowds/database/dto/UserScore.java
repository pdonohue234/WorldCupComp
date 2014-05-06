package com.crowds.database.dto;

import java.io.Serializable;

public class UserScore extends Dto<UserScore> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "User_Scores";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"User_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Predictions_Made", "Predictions_Correct", 
		"Predictions_Incorrect", "Predictions_Pending", "Overall_Score"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int USERID = 0;
	
	private int		m_numPredictionsMade;
	private int		m_numPredictionsCorrect;
	private int		m_numPredictionsIncorrect;
	private int		m_numPredictionsPending;
	private int		m_currentScore;
	
	
	/**
	 * Default Constructor
	 */
	public UserScore() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts UserId
	 */
	public UserScore(String p_userId) {
		super( p_userId );
	}
	
	
	public String getUserId() {
		return (String) m_ids[USERID];
	}
	
	public void setUserId( String p_userId ) {
		this.m_ids[USERID] = p_userId;
	}
	
	
	public int getNumPredictionsMade() {
		return m_numPredictionsMade;
	}


	public void setNumPredictionsMade(int p_numPredictionsMade) {
		m_numPredictionsMade = p_numPredictionsMade;
	}


	public int getNumPredictionsCorrect() {
		return m_numPredictionsCorrect;
	}


	public void setNumPredictionsCorrect(int p_numPredictionsCorrect) {
		m_numPredictionsCorrect = p_numPredictionsCorrect;
	}


	public int getNumPredictionsIncorrect() {
		return m_numPredictionsIncorrect;
	}


	public void setNumPredictionsIncorrect(int p_numPredictionsIncorrect) {
		m_numPredictionsIncorrect = p_numPredictionsIncorrect;
	}


	public int getNumPredictionsPending() {
		return m_numPredictionsPending;
	}


	public void setNumPredictionsPending(int p_numPredictionsPending) {
		m_numPredictionsPending = p_numPredictionsPending;
	}


	public int getCurrentScore() {
		return m_currentScore;
	}


	public void setCurrentScore(int p_currentScore) {
		m_currentScore = p_currentScore;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
