package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Team extends Dto<Team> implements Serializable {
	
	private static final long serialVersionUID = 535781654836189576L;
	
	public static final String MAPPED_TABLE_NAME = "Teams";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Team_ID, Event_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Team_Name"}; 
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int TEAMID = 0;
	public static final int EVENTID = 1;
	
	private String	m_teamName;
	
	/**
	 * Default Constructor
	 */
	public Team() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts TeamId and EventId
	 */
	public Team(String p_teamId, String p_eventId) {
		super( new Object[] { p_teamId, p_eventId } );
	}
	
	public String getTeamId() {
		return (String) m_ids[TEAMID];
	}
	
	public void setTeamId( String p_teamId ) {
		this.m_ids[TEAMID] = p_teamId;
	}
	
	public String getEventId() {
		return (String) m_ids[EVENTID];
	}
	
	public void setEventId( String p_eventId ) {
		this.m_ids[EVENTID] = p_eventId;
	}


	public String getTeamName() {
		return m_teamName;
	}


	public void setTeamName(String p_teamName) {
		m_teamName = p_teamName;
	}

	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
