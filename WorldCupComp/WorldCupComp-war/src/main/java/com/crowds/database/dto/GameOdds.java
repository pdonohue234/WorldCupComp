package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class GameOdds extends Dto<GameOdds> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "GAMEODDS";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"GAMEID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"TEAM1_ODDS", "TEAM2_ODDS", "DRAW_ODDS", "DATE", "TIME"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int GAMEID = 0;
	
	private double	m_teamOneOdds;
	private double	m_teamTwoOdds;
	private double	m_drawOdds;
	private Date	m_date;
	private Integer	m_time; //Hhmmss
	
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


	public final double getTeamOneOdds() {
		return m_teamOneOdds;
	}


	public final void setTeamOneOdds(double p_teamOneOdds) {
		m_teamOneOdds = p_teamOneOdds;
	}


	public final double getTeamTwoOdds() {
		return m_teamTwoOdds;
	}


	public final void setTeamTwoOdds(double p_teamTwoOdds) {
		m_teamTwoOdds = p_teamTwoOdds;
	}


	public final double getDrawOdds() {
		return m_drawOdds;
	}


	public final void setDrawOdds(double p_drawOdds) {
		m_drawOdds = p_drawOdds;
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
