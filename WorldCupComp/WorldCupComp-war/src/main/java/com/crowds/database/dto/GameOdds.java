package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class GameOdds extends Dto<GameOdds> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Game_Odds";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Game_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Team1_Odds", "Team2_Odds", "Draw_Odds", "TimeStampEntered"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int GAMEID = 0;
	
	private double	m_teamOneOdds;
	private double	m_teamTwoOdds;
	private double	m_drawOdds;
	private Date	m_date;
	
	/**
	 * Default Constructor
	 */
	public GameOdds() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts GameId
	 */
	public GameOdds(String p_gameId) {
		super( p_gameId );
	}
	
	
	public String getGameId() {
		return (String) m_ids[GAMEID];
	}
	
	public void setGameId( String p_gameId ) {
		this.m_ids[GAMEID] = p_gameId;
	}


	public double getTeamOneOdds() {
		return m_teamOneOdds;
	}


	public void setTeamOneOdds(double p_teamOneOdds) {
		m_teamOneOdds = p_teamOneOdds;
	}


	public double getTeamTwoOdds() {
		return m_teamTwoOdds;
	}


	public void setTeamTwoOdds(double p_teamTwoOdds) {
		m_teamTwoOdds = p_teamTwoOdds;
	}


	public double getDrawOdds() {
		return m_drawOdds;
	}


	public void setDrawOdds(double p_drawOdds) {
		m_drawOdds = p_drawOdds;
	}


	public Date getDate() {
		return m_date;
	}


	public void setDate(Date p_date) {
		m_date = p_date;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
