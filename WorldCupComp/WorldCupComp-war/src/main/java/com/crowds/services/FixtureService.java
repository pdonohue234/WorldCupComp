package com.crowds.services;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.FixtureDao;
import com.crowds.database.dto.Fixture;

public class FixtureService extends FixtureDao {
	
	public Logger			m_logger	= 	Logger.getLogger(FixtureService.class.getName());
	
	/** 
	 * Find list of all Fixtures in system
	 * 
	 * @return list of Fixtures
	 */
	public List<Fixture> getFixtureList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Fixture records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Fixture based on the gameId and eventId
	 * @param p_gameId
	 * @param p_eventId
	 * @return
	 */
	public Fixture getFixture(String p_gameId, String p_eventId) { 
		try {
			if( StringUtils.isNotBlank(p_gameId) && StringUtils.isNotBlank(p_eventId)) {
				return this.findById(p_gameId, p_eventId); 
			}
			else {
				this.m_logger.warning("Cannot find Fixture record because of empty Keys passed!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Fixture record for Keys: " + p_gameId +" and "+ p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find a single Fixture based on the gameId 
	 * @param p_gameId
	 * @param p_eventId
	 * @return
	 */
	public Fixture getFixtureByGame(String p_gameId) { 
		try {
			if( StringUtils.isNotBlank(p_gameId) ) {
				return this.findByGameId(p_gameId); 
			}
			else {
				this.m_logger.warning("Cannot find Fixture record because of empty Key passed!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Fixture record for Keys: " + p_gameId );
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 	
	
	/**
	 * Find a list of event fixtures based on the eventId 
	 * @param p_eventId
	 * @return
	 */
	public List<Fixture> getEventsFixtures(String p_eventId) { 
		try {
			if(StringUtils.isNotEmpty(p_eventId)) {
				return this.findByEventId(p_eventId); 
			}
			else {
				this.m_logger.warning("Cannot find Event's Fixtures records based on empty eventId key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Event's Fixtures records with eventId: " + p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single Fixture record into the database
	 * @param p_fixture
	 * @return
	 */
	public boolean insertData(Fixture p_fixture) {  
		try {
			if(p_fixture != null && StringUtils.isNotEmpty(p_fixture.getGameId()) && 
					StringUtils.isNotEmpty(p_fixture.getEventId() )) {
				//Check if record exists for that id already
				Fixture fixtureExists = this.findById(p_fixture.getGameId(), p_fixture.getEventId());
				if( fixtureExists == null ) {
					int rowCount = this.insert(p_fixture);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Event Fixture record was not added successfully for Key:" + p_fixture.getGameId()
								+ " and " + p_fixture.getEventId());
				}
				else {
					this.m_logger.warning("Event Fixture record was not added - record already exists "
							+ "for Keys:" + p_fixture.getGameId() + " and " + p_fixture.getEventId());
				}
			}
			else {
				this.m_logger.warning("Cannot add Event Fixture record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create an Event Fixture record for Key: " + p_fixture.getGameId() 
					+ " and " + p_fixture.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single Fixture record in the database
	 * @param p_payment
	 * @return
	 */
	public boolean updateData(Fixture p_fixture) {  
		try {
			if(p_fixture != null && StringUtils.isNotEmpty(p_fixture.getGameId()) && 
					StringUtils.isNotEmpty(p_fixture.getEventId() )) {
				//Ensure record exists for that id already
				Fixture fixtureExists = this.findById(p_fixture.getGameId(), p_fixture.getEventId());
				if( fixtureExists != null ) {
					int rowCount = this.update(p_fixture);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Event Fixture record was not updated successfully for Key:"  + p_fixture.getGameId() 
								+ " and " + p_fixture.getEventId());
				}
				else 
					this.m_logger.warning("Event Fixture record was not updated - record does NOT exists for Key:"  + p_fixture.getGameId() 
							+ " and " + p_fixture.getEventId());
			}
			else {
				this.m_logger.warning("Cannot update Event Fixture record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update an Event Fixture record for Key: "  + p_fixture.getGameId() 
					+ " and " + p_fixture.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove all fixtures records for an event from the database
	 * @param p_eventId
	 * @return
	 */
	public boolean deleteEventData(String p_eventId) {  
		try {
			if(StringUtils.isNotEmpty(p_eventId)) {
				//Check if record exists before attempting to delete it
				List<Fixture> fixtureExists = this.findByEventId(p_eventId);
				if( fixtureExists != null && fixtureExists.size() > 0) {
					for( Fixture fixture : fixtureExists ) {
						int rowCount = this.delete(fixture);
						if(rowCount != -1)
							return true;	
						else
							this.m_logger.warning("Event's Fixture record was not deleted for Key:" + fixture.getGameId() 
									+ " and " + fixture.getEventId());
					}
				}
			}
			else {
				this.m_logger.warning("Cannot delete Event's Fixture record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete Event's Fixture record for Key: " + p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Remove a fixture record from the database
	 * @param p_gameId
	 * @param p_eventId
	 * @return
	 */
	public boolean deleteData(String p_gameId, String p_eventId) {  
		try {
			if(StringUtils.isNotEmpty(p_gameId) && StringUtils.isNotEmpty(p_eventId )) {
				//Check if record exists before attempting to delete it
				Fixture fixtureExists = this.findById(p_gameId, p_eventId);
				if( fixtureExists != null ) {
					int rowCount = this.delete(fixtureExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Fixture record was not deleted for Key:" + p_gameId +" and "+ p_eventId);
			
				}
			}
			else {
				this.m_logger.warning("Cannot delete Fixture record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete Fixture record for Key:" + p_gameId +" and "+ p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	
}
