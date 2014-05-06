package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Event;
import com.crowds.database.sql.Sql;

public class EventDao extends AbstractDaoJdbc<Event>{

	public Logger			m_logger	= 	Logger.getLogger(EventDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Events";
	protected static final String	SQL_TABLE_COLUMNS 	= "Event_ID, Event_Name, Event_Desc, Event_Date";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Event_ID=?";
	protected static final String	SQL_SELECT_USINGNAME= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Event_Name=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Event_Name=?, Event_Desc=?, Event_Date=? WHERE Event_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Event_ID=?";
	
	
	public EventDao() {
		super();
	}
	
	
	/**
	 * Find all of the Events in the Event database table
	 * 
	 * @return list of Events
	 */
	protected List<Event> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Event> events = this.findAll(l_sql, new EventRowMapper());
			return events;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Event record for given unique eventId
	 * @param p_eventId
	 * @return Event record
	 */
	protected Event findById(String p_eventId) {
		try {
			if( StringUtils.isNotBlank(p_eventId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_eventId});
				Event event = this.findById(l_sql, new EventRowMapper());
				return event;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	/**
	 * Find a single Event record for given event name
	 * @param p_eventName
	 * @return Event record
	 */
	protected Event findByEventName(String p_eventName){
		try {
			if( StringUtils.isNotBlank(p_eventName)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGNAME, new Object[] {p_eventName});
				Event event = this.findById(l_sql, new EventRowMapper());
				return event;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Insert a new Event record into the database
	 * @param p_event
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(Event p_event) {
		try {
			if( StringUtils.isNotBlank(p_event.getEventId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_event.getEventId(), p_event.getEventName(), p_event.getEventDescription(), 
						this.getSdf().format(p_event.getEventStartDate())});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return -1;		
	}
	
	/**
	 * Update a Event record in the database
	 * @param p_event
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(Event p_event) {
		try {
			if( StringUtils.isNotBlank(p_event.getEventId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_event.getEventName(), p_event.getEventDescription(), 
										this.getSdf().format(p_event.getEventStartDate()), p_event.getEventId()});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return -1;		
	}	
	
	/**
	 * Remove a Event record from the database
	 * @param p_event
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(Event p_event) {
		try {
			if( StringUtils.isNotBlank(p_event.getEventId())) {
				Sql l_sql = new Sql(SQL_DELETE, new Object[] {p_event.getEventId()});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return -1;		
	}	
	
	
	@Override
	public EventRowMapper newRowMapper() {
		return new EventRowMapper();
	}
	
	/** 
	 * Map the ResultSet to Event Dto
	 *
	 */
	protected class EventRowMapper implements ParameterizedRowMapper<Event> {
		
		public EventRowMapper() {
			super();
		}
		
		public Event mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Event dto = new Event();
			try {
				dto.setEventId(rs.getString(seqn++));
				dto.setEventName(rs.getString(seqn++));
				dto.setEventDescription(rs.getString(seqn++));
				dto.setEventStartDate(getDate(rs.getDate(seqn++)));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
