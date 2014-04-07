package com.crowds.database.dto;

import java.io.Serializable;

public class UserScore extends Dto<UserScore> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "UserScore";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"USERID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"NUM_PREDICTIONS_MADE", "NUM_PREDICTIONS_CORRECT", 
		"NUM_PREDICTIONS_INCORRECT", "NUM_PREDICTIONS_PENDING", "CURRENT_SCORE"}; 
	
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
	
	
	public final int getNumPredictionsMade() {
		return m_numPredictionsMade;
	}


	public final void setNumPredictionsMade(int p_numPredictionsMade) {
		m_numPredictionsMade = p_numPredictionsMade;
	}


	public final int getNumPredictionsCorrect() {
		return m_numPredictionsCorrect;
	}


	public final void setNumPredictionsCorrect(int p_numPredictionsCorrect) {
		m_numPredictionsCorrect = p_numPredictionsCorrect;
	}


	public final int getNumPredictionsIncorrect() {
		return m_numPredictionsIncorrect;
	}


	public final void setNumPredictionsIncorrect(int p_numPredictionsIncorrect) {
		m_numPredictionsIncorrect = p_numPredictionsIncorrect;
	}


	public final int getNumPredictionsPending() {
		return m_numPredictionsPending;
	}


	public final void setNumPredictionsPending(int p_numPredictionsPending) {
		m_numPredictionsPending = p_numPredictionsPending;
	}


	public final int getCurrentScore() {
		return m_currentScore;
	}


	public final void setCurrentScore(int p_currentScore) {
		m_currentScore = p_currentScore;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
