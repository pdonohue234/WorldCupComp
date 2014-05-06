package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.GameOddsDao;
import com.crowds.database.dto.GameOdds;

public class GameOddsService extends GameOddsDao {
	
	/** 
	 * Find list of all GameOdds in system
	 * 
	 * @return list of GameOdds
	 */
	public List<GameOdds> getGameOddsList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL GameOdds records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single GameOdds based on their gameId
	 * @param p_gameId
	 * @return GameOdds
	 */
	public GameOdds getGameOdds(String p_gameId) { 
		try {
			if(StringUtils.isNotEmpty(p_gameId)) {
				return this.findById(p_gameId); 
			}
			else {
				this.m_logger.warning("Cannot find a GameOdds record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find GameOdds record for Key: " + p_gameId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
			
	
	/** 
	 * Insert a single GameOdds record into the database
	 * @param p_gameOdds
	 * @return
	 */
	public boolean insertData(GameOdds p_gameOdds) {  
		try {
			if(p_gameOdds != null && StringUtils.isNotEmpty(p_gameOdds.getGameId())) {
				//Check if record exists for that id already
				GameOdds gameOddsExists = this.findById(p_gameOdds.getGameId());
				if( gameOddsExists == null ) {
					int rowCount = this.insert(p_gameOdds);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameOdds record was not added successfully for Key:" + p_gameOdds.getGameId());
				}
				else {
					this.m_logger.warning("GameOdds record was not added - record already exists for Key:" + p_gameOdds.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot add GameOdds record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a GameOdds record for Key: " + p_gameOdds.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single GameOdds record in the database
	 * @param p_gameOdds
	 * @return
	 */
	public boolean updateData(GameOdds p_gameOdds) {  
		try {
			if(p_gameOdds != null && StringUtils.isNotEmpty(p_gameOdds.getGameId())) {
				//Ensure record exists for that id already
				GameOdds gameOddsExists = this.findById(p_gameOdds.getGameId());
				if( gameOddsExists != null ) {
					int rowCount = this.update(p_gameOdds);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameOdds record was not updated successfully for Key:" + p_gameOdds.getGameId());
				}
				else {
					this.m_logger.warning("GameOdds record was not updated - record does NOT exists for Key:" + p_gameOdds.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot update GameOdds record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a GameOdds record for Key: " + p_gameOdds.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove an GameOdds record from the database
	 * @param p_gameId
	 * @return
	 */
	public boolean deleteData(String p_gameId) {  
		try {
			if(StringUtils.isNotEmpty(p_gameId)) {
				//Check if record exists before attempting to delete it
				GameOdds gameOddsExists = this.findById(p_gameId);
				if( gameOddsExists != null ) {
					int rowCount = this.delete(gameOddsExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameOdds record was not deleted for Key:" + p_gameId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete GameOdds record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete GameOdds record for Key: " + p_gameId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
