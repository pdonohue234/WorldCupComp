package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Prediction extends Dto<Prediction> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Prediction";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"USERID", "GAMEID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"PREDICTION", "DATE", "TIME"};
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int USERID = 0;
	public static final int GAMEID = 1;	
	
	private String	m_prediction;
	private Date	m_date;
	private Integer	m_time; //Hhmmss
	
	
	/**
	 * Default Constructor
	 */
	public Prediction() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	/**
	 * Overloaded Constructor - Accepts UserId
	 */
	public Prediction( String p_userId ) {
		super( new Object[] { p_userId, "" } );
	}	
	
	
	/**
	 * Overloaded Constructor - Accepts UserId & GameId
	 */
	public Prediction(String p_userId, String p_gameId) {
		super( new Object[] { p_userId, p_gameId } );
	}
	
	
	public String getUserId() {
		return (String) m_ids[USERID];
	}
	
	public void setUserId( String p_userId ) {
		this.m_ids[USERID] = p_userId;
	}
	
	public String getGameId() {
		return (String) m_ids[GAMEID];
	}
	
	public void setGameId( String p_gameId ) {
		this.m_ids[GAMEID] = p_gameId;
	}
	
	
	public final String getPrediction() {
		return m_prediction;
	}

	public final void setPrediction(String p_prediction) {
		m_prediction = p_prediction;
	}

	public final Date getDate() {
		return m_date;
	}

	public final void setDate(Date p_date) {
		m_date = p_date;
	}

	public final Integer getTime() {
		return m_time;
	}

	public final void setTime(Integer p_time) {
		m_time = p_time;
	}

	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
