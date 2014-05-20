package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Prediction extends Dto<Prediction> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Predictions";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"User_ID", "Game_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Team1Prediction", "Team2Prediction", "Result_Prediction", "TimeStamp"};
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int USERID = 0;
	public static final int GAMEID = 1;	
	
	private int		m_team1Prediction;
	private int		m_team2Prediction;
	private String	m_winningTeamPrediction;
	private Date	m_date;
	
	
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
	
	
	public int getTeam1Prediction() {
		return m_team1Prediction;
	}

	public void setTeam1Prediction(int p_team1Prediction) {
		m_team1Prediction = p_team1Prediction;
	}
	
	public int getTeam2Prediction() {
		return m_team2Prediction;
	}

	public void setTeam2Prediction(int p_team2Prediction) {
		m_team2Prediction = p_team2Prediction;
	}

	public Date getDate() {
		return m_date;
	}

	public void setDate(Date p_date) {
		m_date = p_date;
	}
	
	public int whichTeamToWin() {
		if(this.m_team1Prediction > this.m_team2Prediction) 
			return 1;
		else if(this.m_team2Prediction > this.m_team1Prediction)
			return 2;
		else if(this.m_team2Prediction == this.m_team1Prediction)
			return 0;
		else
			return -1;
	}
	
	public String getWinningTeamPrediction() {
		return this.m_winningTeamPrediction;
	}
	
	public void setWinningTeamPrediction( String p_winningTeamPrediction ) {
		this.m_winningTeamPrediction = p_winningTeamPrediction;
	}

	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
