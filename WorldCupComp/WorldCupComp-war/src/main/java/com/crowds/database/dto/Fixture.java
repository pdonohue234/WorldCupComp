package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Fixture extends Dto<Fixture> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Fixtures";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Game_ID", "Event_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Game_Date", "Team1", "Team1_Score", "Team2", "Team2_Score", 
		"Game_Played", "Winning_Team", "Round", "Game_Location"};
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int GAMEID = 0;
	public static final int EVENTID = 1;
	
	private Date	m_gameDate;
	private String	m_teamOne;
	private int		m_teamOneScore;
	private String	m_teamTwo;
	private int		m_teamTwoScore;
	private String	m_gamePlayed;
	private String	m_winningTeam;
	private String	m_round;
	private String	m_gameLocation;
	
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

	public Date getGameDate() {
		return m_gameDate;
	}


	public void setGameDate(Date p_gameDate) {
		m_gameDate = p_gameDate;
	}


	public String getTeamOne() {
		return m_teamOne;
	}


	public void setTeamOne(String p_teamOne) {
		m_teamOne = p_teamOne;
	}

	public int getTeamOneScore() {
		return m_teamOneScore;
	}


	public void setTeamOneScore(int p_teamOneScore) {
		m_teamOneScore = p_teamOneScore;
	}


	public String getTeamTwo() {
		return m_teamTwo;
	}


	public void setTeamTwo(String p_teamTwo) {
		m_teamTwo = p_teamTwo;
	}

	public int getTeamTwoScore() {
		return m_teamTwoScore;
	}


	public void setTeamTwoScore(int p_teamTwoScore) {
		m_teamTwoScore = p_teamTwoScore;
	}
	
	public String getGamePlayed() {
		return m_gamePlayed;
	}


	public void setGamePlayed(String p_gamePlayed) {
		m_gamePlayed = p_gamePlayed;
	}
	
	public String getWinningTeam() {
		return m_winningTeam;
	}


	public void setWinningTeam(String p_winningTeam) {
		m_winningTeam = p_winningTeam;
	}
	
	public String getRound() {
		return m_round;
	}


	public void setRound(String p_round) {
		m_round = p_round;
	}
	
	public String getGameLocation() {
		return m_gameLocation;
	}


	public void setGameLocation(String p_gameLocation) {
		m_gameLocation = p_gameLocation;
	}

	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
