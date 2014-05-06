package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.UserScoreDao;
import com.crowds.database.dto.UserScore;

public class UserScoreService extends UserScoreDao {
	
	/** 
	 * Find list of all UserScores in system
	 * 
	 * @return list of UserScores
	 */
	public List<UserScore> getUserScoreList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL UserScore records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single UserScore based on their userId
	 * @param p_userId
	 * @return UserScore record
	 */
	public UserScore getUserScore(String p_userId) { 
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				return this.findById(p_userId); 
			}
			else {
				this.m_logger.warning("Cannot find a UserScore record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find UserScore record for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find a list of the top userScores based on the field CURRENT_SCORE
	 * @param num of topScores you want returned
	 * @return
	 */
	public List<UserScore> getTopUserScores(int p_num) { 
		try {
			if(p_num != 0) {
				return this.findTopUserScores(p_num); 
			}
			else {
				return this.findTopUserScores(10); 
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find the top UserScore records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single userScore record into the database
	 * @param p_userScore
	 * @return
	 */
	public boolean insertData(UserScore p_userScore) {  
		try {
			if(p_userScore != null && StringUtils.isNotEmpty(p_userScore.getUserId())) {
				//Check if record exists for that userId already
				UserScore userScoreExists = this.findById(p_userScore.getUserId());
				if( userScoreExists == null ) {
					int rowCount = this.insert(p_userScore);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("UserScore record was not added successfully for Key:" + p_userScore.getUserId());
				}
				else {
					this.m_logger.warning("UserScore record was not added - UserScore record already exists for Key:" + p_userScore.getUserId());
				}
			}
			else {
				this.m_logger.warning("Cannot add UserScore record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a UserScore record for Key: " + p_userScore.getUserId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single userScore record in the database
	 * @param p_userScore
	 * @return
	 */
	public boolean updateData(UserScore p_userScore) {  
		try {
			if(p_userScore != null && StringUtils.isNotEmpty(p_userScore.getUserId())) {
				//Ensure record exists for that userId already
				UserScore userScoreExists = this.findById(p_userScore.getUserId());
				if( userScoreExists != null ) {
					int rowCount = this.update(p_userScore);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("UserScore record was not updated successfully for Key:" + 
								p_userScore.getUserId());
				}
				else {
					this.m_logger.warning("UserScore record was not updated - UserScore record does NOT exists for Key:" 
							+ p_userScore.getUserId());
				}
			}
			else {
				this.m_logger.warning("Cannot update UserScore record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a UserScore record for Key: " + p_userScore.getUserId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove a userScore record from the database
	 * @param p_userId
	 * @return
	 */
	public boolean deleteData(String p_userId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				//Check if record exists before attempting to delete it
				UserScore userScoreExists = this.findById(p_userId);
				if( userScoreExists != null ) {
					int rowCount = this.delete(userScoreExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("UserScore record was not deleted for Key:" + p_userId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete UserScore record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete UserScore record for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
