package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Fixture extends Dto<Fixture> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "FIXTURE";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"GAMEID", "EVENTID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"GAME_TITLE", "GAME_DATE", "GAME_TIME", "TEAM1", 
		"TEAM2", "ROUND", "LOCATION"};
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int GAMEID = 0;
	public static final int EVENTID = 1;
	
	private String	m_gameTitle;
	private Date	m_gameDate;
	private Integer	m_gameTime; //Hhmmss
	private String	m_teamOne;
	private String	m_teamTwo;
	private String	m_round;
	private String 	m_location;
	
	
	/**
	 * Default Constructor
	 */
	public Fixture() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts GameId
	 */
	public Fixture(String p_gameId) {
		super( new Object[] { p_gameId, "" } );
	}
	
	/**
	 * Overloaded Constructor - Accepts EventId & GameId
	 */
	public Fixture(String p_gameId, String p_eventId ) {
		super( new Object[] { p_gameId, p_eventId } );
	}
	
	
	public String getGameId() {
		return (String) m_ids[GAMEID];
	}
	
	public void setGameId( String p_gameId ) {
		this.m_ids[GAMEID] = p_gameId;
	}
	
	public String getEventId() {
		return (String) m_ids[EVENTID];
	}
	
	public void setEventId( String p_eventId ) {
		this.m_ids[EVENTID] = p_eventId;
	}

	public final Date getGameDate() {
		return m_gameDate;
	}


	public final void setGameDate(Date p_gameDate) {
		m_gameDate = p_gameDate;
	}


	public final Integer getGameTime() {
		return m_gameTime;
	}


	public final void setGameTime(Integer p_gameTime) {
		m_gameTime = p_gameTime;
	}


	public final String getGameTitle() {
		return m_gameTitle;
	}


	public final void setGameTitle(String p_gameTitle) {
		m_gameTitle = p_gameTitle;
	}


	public final String getTeamOne() {
		return m_teamOne;
	}


	public final void setTeamOne(String p_teamOne) {
		m_teamOne = p_teamOne;
	}


	public final String getTeamTwo() {
		return m_teamTwo;
	}


	public final void setTeamTwo(String p_teamTwo) {
		m_teamTwo = p_teamTwo;
	}


	public final String getRound() {
		return m_round;
	}


	public final void setRound(String p_round) {
		m_round = p_round;
	}


	public final String getLocation() {
		return m_location;
	}


	public final void setLocation(String p_location) {
		m_location = p_location;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
