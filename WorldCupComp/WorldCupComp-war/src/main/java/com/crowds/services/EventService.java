package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.EventDao;
import com.crowds.database.dto.Event;

public class EventService extends EventDao {
	
	/** 
	 * Find list of all Events in system
	 * 
	 * @return list of Events
	 */
	public List<Event> getEventList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Event records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Event based on their eventId
	 * @param p_eventId
	 * @return Event
	 */
	public Event getEvent(String p_eventId) { 
		try {
			if(StringUtils.isNotEmpty(p_eventId)) {
				return this.findById(p_eventId); 
			}
			else {
				this.m_logger.warning("Cannot find a Event record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Event record for Key: " + p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find an Event based on event name
	 * @param p_eventName
	 * @return Event
	 */
	public Event getEventByName(String p_eventName) { 
		try {
			if(StringUtils.isNotEmpty(p_eventName)) {
				return this.findByEventName(p_eventName); 
			}
			else {
				this.m_logger.warning("Cannot find Event record based on empty event name!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Event record with event name: " + p_eventName);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/**
	 * Find an Event based on event name and country
	 * @param p_eventName
	 * @param p_eventCountry
	 * @return Event
	 */
	public Event getEventByNameAndCountry(String p_eventName, String p_eventCountry) { 
		try {
			if(StringUtils.isNotEmpty(p_eventName) && StringUtils.isNotEmpty(p_eventCountry)) {
				return this.findByEventNameAndCountry(p_eventName, p_eventCountry); 
			}
			else {
				this.m_logger.warning("Cannot find Event record based on empty event name/country!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Event record with event name: " + p_eventName 
					+ " and event country: " + p_eventCountry);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single Event record into the database
	 * @param p_event
	 * @return
	 */
	public boolean insertData(Event p_event) {  
		try {
			if(p_event != null && StringUtils.isNotEmpty(p_event.getEventId())) {
				//Check if record exists for that id already
				Event eventExists = this.findById(p_event.getEventId());
				if( eventExists == null ) {
					int rowCount = this.insert(p_event);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Event record was not added successfully for Key:" + p_event.getEventId());
				}
				else {
					this.m_logger.warning("Event record was not added - record already exists for Key:" + p_event.getEventId());
				}
			}
			else {
				this.m_logger.warning("Cannot add Event record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a Event record for Key: " + p_event.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single Event record in the database
	 * @param p_event
	 * @return
	 */
	public boolean updateData(Event p_event) {  
		try {
			if(p_event != null && StringUtils.isNotEmpty(p_event.getEventId())) {
				//Ensure record exists for that id already
				Event eventExists = this.findById(p_event.getEventId());
				if( eventExists != null ) {
					int rowCount = this.update(p_event);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Event record was not updated successfully for Key:" + p_event.getEventId());
				}
				else {
					this.m_logger.warning("Event record was not updated - record does NOT exists for Key:" + p_event.getEventId());
				}
			}
			else {
				this.m_logger.warning("Cannot update Event record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a Event record for Key: " + p_event.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove an Event record from the database
	 * @param p_eventId
	 * @return
	 */
	public boolean deleteData(String p_eventId) {  
		try {
			if(StringUtils.isNotEmpty(p_eventId)) {
				//Check if record exists before attempting to delete it
				Event eventExists = this.findById(p_eventId);
				if( eventExists != null ) {
					int rowCount = this.delete(eventExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Event record was not deleted for Key:" + p_eventId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete Event record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete Event record for Key: " + p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
