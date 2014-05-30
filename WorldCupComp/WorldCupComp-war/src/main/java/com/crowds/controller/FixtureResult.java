package com.crowds.controller;

import java.util.Date;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.TimeZone;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dto.Fixture;
import com.crowds.database.dto.Prediction;

public class FixtureResult {

	public Logger			m_logger	= 	Logger.getLogger(FixtureResult.class.getName());
	
	private String	userId;
	private String	gameId;
	private String	eventId;
	private Date	gameDate;
	private String	gameDateAsString;
	private String	teamOne;
	private String	teamOneFullName;
	private String	teamOneScore;
	private String	teamTwo;
	private String	teamTwoFullName;
	private String	teamTwoScore;
	private String	gamePlayed;
	private String	winningTeam;
	private String	round;
	private String	gameLocation;
	private boolean active;
	private String	result;
	
	/**
	 * Default Constructor
	 */
	public FixtureResult() {
	}
	
	/**
	 * Overloaded Constructor - Accepts GameId
	 */
	public FixtureResult(Fixture fixture, String userId) {
		this.userId = userId;
		this.gameId = fixture.getGameId();
		this.eventId = fixture.getEventId();
		this.gameDate = fixture.getGameDate();
		if(this.gameDate != null)
			gameDateAsString = convertDateToString();
		this.teamOne = fixture.getTeamOne();
		this.teamOneFullName = fixture.getTeamOneFullName();
		this.teamOneScore = "-";
		this.teamTwo = fixture.getTeamTwo();	
		this.teamTwoFullName = fixture.getTeamTwoFullName();
		this.teamTwoScore = "-";
		this.gamePlayed = fixture.getGamePlayed();
		this.winningTeam = fixture.getWinningTeam();
		this.round = fixture.getRound();
		this.gameLocation = fixture.getGameLocation();
		
		hasGameStarted();
		
		if(fixture.isGamePlayed()) {
			this.result = fixture.getTeamOneScore() +"-" + fixture.getTeamTwoScore();
		} else 
			this.result = "-";		
	}
	
	/**
	 * Overloaded Constructor - Accepts GameId
	 */
	public FixtureResult(Fixture fixture, Prediction prediction) {
		this.userId = prediction.getUserId();
		this.gameId = fixture.getGameId();
		this.eventId = fixture.getEventId();
		this.gameDate = fixture.getGameDate();
		if(this.gameDate != null)
			gameDateAsString = convertDateToString();		
		this.teamOne = fixture.getTeamOne();
		this.teamOneFullName = fixture.getTeamOneFullName();
		this.teamTwo = fixture.getTeamTwo();
		this.teamTwoFullName = fixture.getTeamTwoFullName();
		this.teamOneScore = prediction.getTeam1Prediction() +"";
		this.teamTwoScore = prediction.getTeam2Prediction() +"";
		this.winningTeam = prediction.getWinningTeamPrediction();
		this.gamePlayed = fixture.getGamePlayed();
		this.round = fixture.getRound();
		this.gameLocation = fixture.getGameLocation();
		hasGameStarted();
		
		if(fixture.isGamePlayed()) {
			this.result = fixture.getTeamOneScore() +"-" + fixture.getTeamTwoScore();
		} else 
			this.result = "-";			
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId( String p_userId ) {
		this.userId = p_userId;
	}
	
	public String getGameId() {
		return gameId;
	}
	
	public void setGameId( String p_gameId ) {
		this.gameId = p_gameId;
	}
	
	public String getEventId() {
		return eventId;
	}
	
	public void setEventId( String p_eventId ) {
		this.eventId = p_eventId;
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
	
	public String getTeamOneFullName() {
		return teamOneFullName;
	}


	public void setTeamOneFullName(String p_teamOneFullName) {
		teamOneFullName = p_teamOneFullName;
	}

	public String getTeamOneScore() {
		return teamOneScore;
	}


	public void setTeamOneScore(String p_teamOneScore) {
		teamOneScore = p_teamOneScore;
	}


	public String getTeamTwo() {
		return teamTwo;
	}


	public void setTeamTwo(String p_teamTwo) {
		teamTwo = p_teamTwo;
	}
	
	public String getTeamTwoFullName() {
		return teamTwoFullName;
	}


	public void setTeamTwoFullName(String p_teamTwoFullName) {
		teamTwoFullName = p_teamTwoFullName;
	}

	public String getTeamTwoScore() {
		return teamTwoScore;
	}


	public void setTeamTwoScore(String p_teamTwoScore) {
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
	
	public void hasGameStarted() {
		Date now = new Date();
		if(gameDate.compareTo(now) <= 0) {
			this.active = false;
		} 
		else if(isGamePlayed()) {
			this.active = false;
		}
		else {
			this.active = true;
		}
	}
	
	public boolean getActive() {
		return active;
	}


	public void setActive(boolean p_active) {
		active = p_active;
	}
	
	public boolean isActive() {
		if(this.active)
			return true;
		else
			return false;
	}
	
	public boolean isChanged() {
		if(StringUtils.isNotBlank(this.winningTeam)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean keysAreSet() {
		if(StringUtils.isNotBlank(this.userId) && StringUtils.isNotBlank(this.gameId) && StringUtils.isNotBlank(this.eventId)) {
			return true;
		}
		else
			return false;
	}
	
	public boolean scoresAreSet() {
		if(StringUtils.isNotBlank(this.teamOneScore) && StringUtils.isNotBlank(this.teamTwoScore)) {
			return true;
		}
		else
			return false;
	}
	
	public void setResult(String p_result) {
		result = p_result;
	}
	
	public String getResult() {
		return result;
	}	
	
	public void setGameDateAsString(String p_gameDateAsString) {
		gameDateAsString = p_gameDateAsString;
	}
	
	public String getGameDateAsString() {
		return gameDateAsString;
	}	
	
	public String convertDateToString() {		
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM ha");
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Dublin"));
		DateFormatSymbols symbols = new DateFormatSymbols();
	    symbols.setAmPmStrings(new String[] { "am", "pm" });
	    sdf.setDateFormatSymbols(symbols);
	    return sdf.format(this.gameDate.getTime());
	}
}
