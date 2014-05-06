package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.GameResultDao;
import com.crowds.database.dto.GameResult;

public class GameResultService extends GameResultDao {
	
	/** 
	 * Find list of all GameResults in system
	 * 
	 * @return list of GameResults
	 */
	public List<GameResult> getGameResultList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL GameResults records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single GameResult based on their gameId
	 * @param p_gameId
	 * @return GameResult
	 */
	public GameResult getGameResult(String p_gameId) { 
		try {
			if(StringUtils.isNotEmpty(p_gameId)) {
				return this.findById(p_gameId); 
			}
			else {
				this.m_logger.warning("Cannot find a GameResult record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find GameResult record for Key: " + p_gameId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
			
	
	/** 
	 * Insert a single GameResult record into the database
	 * @param p_gameResult
	 * @return
	 */
	public boolean insertData(GameResult p_gameResult) {  
		try {
			if(p_gameResult != null && StringUtils.isNotEmpty(p_gameResult.getGameId())) {
				//Check if record exists for that id already
				GameResult gameResultExists = this.findById(p_gameResult.getGameId());
				if( gameResultExists == null ) {
					int rowCount = this.insert(p_gameResult);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameResult record was not added successfully for Key:" + p_gameResult.getGameId());
				}
				else {
					this.m_logger.warning("GameResult record was not added - record already exists for Key:" + p_gameResult.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot add GameResult record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a GameResult record for Key: " + p_gameResult.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single GameResult record in the database
	 * @param p_gameOdds
	 * @return
	 */
	public boolean updateData(GameResult p_gameResult) {  
		try {
			if(p_gameResult != null && StringUtils.isNotEmpty(p_gameResult.getGameId())) {
				//Ensure record exists for that id already
				GameResult gameResultExists = this.findById(p_gameResult.getGameId());
				if( gameResultExists != null ) {
					int rowCount = this.update(p_gameResult);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameResult record was not updated successfully for Key:" + p_gameResult.getGameId());
				}
				else {
					this.m_logger.warning("GameResult record was not updated - record does NOT exists for Key:" + p_gameResult.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot update GameResult record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a GameResult record for Key: " + p_gameResult.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove an GameResult record from the database
	 * @param p_gameId
	 * @return
	 */
	public boolean deleteData(String p_gameId) {  
		try {
			if(StringUtils.isNotEmpty(p_gameId)) {
				//Check if record exists before attempting to delete it
				GameResult gameResultExists = this.findById(p_gameId);
				if( gameResultExists != null ) {
					int rowCount = this.delete(gameResultExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("GameResult record was not deleted for Key:" + p_gameId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete GameResult record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete GameResult record for Key: " + p_gameId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
