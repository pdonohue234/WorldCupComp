package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Event extends Dto<Event> implements Serializable {
	
	private static final long serialVersionUID = 535781654836189576L;
	
	public static final String MAPPED_TABLE_NAME = "Events";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Event_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Event_Name", "Event_Desc", "Event_Date"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int EVENTID = 0;
	
	private String	m_eventName;
	private String	m_eventDescription;
	private Date	m_eventStartDate;
	
	/**
	 * Default Constructor
	 */
	public Event() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts EventId
	 */
	public Event(String p_eventId) {
		super( p_eventId );
	}
	
	
	public String getEventId() {
		return (String) m_ids[EVENTID];
	}
	
	public void setEventId( String p_eventId ) {
		this.m_ids[EVENTID] = p_eventId;
	}


	public String getEventName() {
		return m_eventName;
	}


	public void setEventName(String p_eventName) {
		m_eventName = p_eventName;
	}


	public String getEventDescription() {
		return m_eventDescription;
	}


	public void setEventDescription(String p_eventDescription) {
		m_eventDescription = p_eventDescription;
	}


	public Date getEventStartDate() {
		return m_eventStartDate;
	}


	public void setEventStartDate(Date p_eventStartDate) {
		m_eventStartDate = p_eventStartDate;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
