package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class Fixture extends Dto<Fixture> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Fixtures";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Game_ID", "Event_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Game_Date", "Team1", "Team1_Score", "Team2", "Team2_Score", 
		"Game_Played", "Winning_Team", "Round", "Game_Location"};
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int GAMEID = 0;
	public static final int EVENTID = 1;
	
	private Date	gameDate;
	private String	teamOne;
	private int		teamOneScore;
	private String	teamTwo;
	private int		teamTwoScore;
	private String	gamePlayed;
	private String	winningTeam;
	private String	round;
	private String	gameLocation;
	
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
		return gameDate;
	}


	public void setGameDate(Date p_gameDate) {
		gameDate = p_gameDate;
	}


	public String getTeamOne() {
		return teamOne;
	}


	public void setTeamOne(String p_teamOne) {
		teamOne = p_teamOne;
	}

	public int getTeamOneScore() {
		return teamOneScore;
	}


	public void setTeamOneScore(int p_teamOneScore) {
		teamOneScore = p_teamOneScore;
	}


	public String getTeamTwo() {
		return teamTwo;
	}


	public void setTeamTwo(String p_teamTwo) {
		teamTwo = p_teamTwo;
	}

	public int getTeamTwoScore() {
		return teamTwoScore;
	}


	public void setTeamTwoScore(int p_teamTwoScore) {
		teamTwoScore = p_teamTwoScore;
	}
	
	public String getGamePlayed() {
		return gamePlayed;
	}


	public void setGamePlayed(String p_gamePlayed) {
		gamePlayed = p_gamePlayed;
	}
	
	public boolean isGamePlayed() {
		if(StringUtils.isNotEmpty(this.gamePlayed)){
			if(StringUtils.equalsIgnoreCase(this.gamePlayed, "Y") ||
				StringUtils.equalsIgnoreCase(this.gamePlayed, "Yes") ||
				StringUtils.equalsIgnoreCase(this.gamePlayed, "T") ||
				StringUtils.equalsIgnoreCase(this.gamePlayed, "True"))
				return true;
			else
				return false;
		}
		else
			return false;
	}		
	
	public String getWinningTeam() {
		return winningTeam;
	}


	public void setWinningTeam(String p_winningTeam) {
		winningTeam = p_winningTeam;
	}
	
	public String getRound() {
		return round;
	}


	public void setRound(String p_round) {
		round = p_round;
	}
	
	public String getGameLocation() {
		return gameLocation;
	}


	public void setGameLocation(String p_gameLocation) {
		gameLocation = p_gameLocation;
	}
	
	public int whichTeamWon() {
		if(isGamePlayed()) {
			if(StringUtils.equalsIgnoreCase(this.teamOne,this.winningTeam) )
				return 1;
			else if(StringUtils.equalsIgnoreCase(this.teamTwo,this.winningTeam) )
				return 2;
			else 
				return 0;
		}
		else
			return -1;
	}

	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
