package com.crowds.database.dto;

import java.io.Serializable;

public class GameResult extends Dto<GameResult> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "GAMERESULT";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"GAMEID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"TEAM1_SCORE", "TEAM2_SCORE", "WINNING_TEAM"};
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int GAMEID = 0;
	
	private int		m_teamOneScore;
	private int		m_teamTwoScore;
	private String	m_winningTeam;
	
	
	/**
	 * Default Constructor
	 */
	public GameResult() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts GameId
	 */
	public GameResult(String p_gameId) {
		super( p_gameId );
	}
	
	
	public String getGameId() {
		return (String) m_ids[GAMEID];
	}
	
	public void setGameId( String p_gameId ) {
		this.m_ids[GAMEID] = p_gameId;
	}
	
	
	public final int getTeamOneScore() {
		return m_teamOneScore;
	}


	public final void setTeamOneScore(int p_teamOneScore) {
		m_teamOneScore = p_teamOneScore;
	}


	public final int getTeamTwoScore() {
		return m_teamTwoScore;
	}


	public final void setTeamTwoScore(int p_teamTwoScore) {
		m_teamTwoScore = p_teamTwoScore;
	}


	public final String getWinningTeam() {
		return m_winningTeam;
	}


	public final void setWinningTeam(String p_winningTeam) {
		m_winningTeam = p_winningTeam;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
