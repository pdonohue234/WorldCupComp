package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.TeamDao;
import com.crowds.database.dto.Team;

public class TeamService extends TeamDao {
	
	/** 
	 * Find list of all Teams in system
	 * 
	 * @return list of Teams
	 */
	public List<Team> getTeamList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Team records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Team based on their teamId and eventId
	 * @param p_teamId
	 * @param p_eventId
	 * @return Team
	 */
	public Team getTeam(String p_teamId, String p_eventId) { 
		try {
			if(StringUtils.isNotEmpty(p_teamId) && StringUtils.isNotEmpty(p_eventId)) {
				return this.findById(p_teamId, p_eventId); 
			}
			else {
				this.m_logger.warning("Cannot find a Team record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Team record for Key: " + p_teamId +" and "+ p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find an Teams based on event id
	 * @param p_eventId
	 * @return List of Teams
	 */
	public List<Team> getEventTeams(String p_eventId) { 
		try {
			if(StringUtils.isNotEmpty(p_eventId)) {
				return this.findByEvent(p_eventId); 
			}
			else {
				this.m_logger.warning("Cannot find list of Team record based on empty event id!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find list of team record with event id: " + p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single Team record into the database
	 * @param p_team
	 * @return
	 */
	public boolean insertData(Team p_team) {  
		try {
			if(p_team != null && StringUtils.isNotEmpty(p_team.getTeamId()) && StringUtils.isNotEmpty(p_team.getEventId())) {
				//Check if record exists for that id already
				Team teamExists = this.findById(p_team.getTeamId(), p_team.getEventId());
				if( teamExists == null ) {
					int rowCount = this.insert(p_team);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Team record was not added successfully for Key:" + p_team.getTeamId() +" and "+ p_team.getEventId());
				}
				else {
					this.m_logger.warning("Team record was not added - record already exists for Key:" + p_team.getTeamId() +" and "+ p_team.getEventId());
				}
			}
			else {
				this.m_logger.warning("Cannot add Team record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a Team record for Key: " + p_team.getTeamId() +" and "+ p_team.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single Team record in the database
	 * @param p_team
	 * @return
	 */
	public boolean updateData(Team p_team) {  
		try {
			if(p_team != null && StringUtils.isNotEmpty(p_team.getTeamId()) && StringUtils.isNotEmpty(p_team.getEventId())) {
				//Ensure record exists for that id already
				Team teamExists = this.findById(p_team.getTeamId(), p_team.getEventId());
				if( teamExists != null ) {
					int rowCount = this.update(p_team);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Team record was not updated successfully for Key:" + p_team.getTeamId() +" and "+ p_team.getEventId());
				}
				else {
					this.m_logger.warning("Team record was not updated - record does NOT exists for Key:" + p_team.getTeamId() +" and "+ p_team.getEventId());
				}
			}
			else {
				this.m_logger.warning("Cannot update Team record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a Team record for Key: " + p_team.getTeamId() +" and "+ p_team.getEventId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove an Team record from the database
	 * @param p_teamId
	 * @param p_eventId
	 * @return
	 */
	public boolean deleteData(String p_teamId, String p_eventId) {  
		try {
			if(StringUtils.isNotEmpty(p_teamId) && StringUtils.isNotEmpty(p_eventId)) {
				//Check if record exists before attempting to delete it
				Team teamExists = this.findById(p_teamId, p_eventId);
				if( teamExists != null ) {
					int rowCount = this.delete(teamExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Team record was not deleted for Key:" + p_teamId +" and "+ p_eventId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete Team record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete Team record for Key: " + p_teamId +" and "+ p_eventId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
