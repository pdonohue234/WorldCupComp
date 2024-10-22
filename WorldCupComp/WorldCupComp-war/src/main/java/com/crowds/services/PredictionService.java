package com.crowds.services;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.PredictionDao;
import com.crowds.database.dto.Fixture;
import com.crowds.database.dto.Prediction;

public class PredictionService extends PredictionDao {
	
	public Logger			m_logger	= 	Logger.getLogger(PredictionService.class.getName());
	
	/** 
	 * Find list of all Predictions in system
	 * 
	 * @return list of Predictions
	 */
	public List<Prediction> getPredictionList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Prediction records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Prediction based on the userId and gameId
	 * @param p_userId
	 * @param p_gameId
	 * @return
	 */
	public Prediction getPrediction(String p_userId, String p_gameId) { 
		try {
			if( StringUtils.isNotBlank(p_userId) && StringUtils.isNotBlank(p_gameId)) {
				return this.findById(p_userId, p_gameId); 
			}
			else {
				this.m_logger.warning("Cannot find User's Prediction record because of empty Keys passed!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User's Prediction record for Keys: " + p_userId +" and "+ p_gameId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find a list of users predictions based on the userId 
	 * @param p_userId
	 * @return
	 */
	public List<Prediction> getUsersPredictions(String p_userId) { 
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				return this.findByUserId(p_userId); 
			}
			else {
				this.m_logger.warning("Cannot find User's Predictions records based on empty userId key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User's Predictions records with userId: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}
	
	/**
	 * If record does not exist then insert it
	 */
	public boolean saveOrUpdate(Prediction p_prediction) {
		try {
			if(p_prediction != null && StringUtils.isNotEmpty(p_prediction.getUserId()) && 
					StringUtils.isNotEmpty(p_prediction.getGameId() )) {
				//Check if record exists for that id already
				Prediction predictionExists = this.findById(p_prediction.getUserId(), p_prediction.getGameId());
				if( predictionExists == null ) {
					int rowCount = this.insert(p_prediction);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Prediction record was not added successfully for Key:" + p_prediction.getUserId()
								+ " and " + p_prediction.getGameId());
				}
				else {
					int rowCount = this.update(p_prediction);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Prediction record was not updated successfully for Key:"  + p_prediction.getUserId() + 
								" and " + p_prediction.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot add User's Prediction record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update/Save a User's Prediction record for Key: " + p_prediction.getUserId() + 
					" and " + p_prediction.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}
	
	/** 
	 * Insert a single Prediction record into the database
	 * @param p_payment
	 * @return
	 */
	public boolean insertData(Prediction p_prediction) {  
		try {
			if(p_prediction != null && StringUtils.isNotEmpty(p_prediction.getUserId()) && 
					StringUtils.isNotEmpty(p_prediction.getGameId() )) {
				//Check if record exists for that id already
				Prediction predictionExists = this.findById(p_prediction.getUserId(), p_prediction.getGameId());
				if( predictionExists == null ) {
					int rowCount = this.insert(p_prediction);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Prediction record was not added successfully for Key:" + p_prediction.getUserId()
								+ " and " + p_prediction.getGameId());
				}
				else {
					this.m_logger.warning("User's Prediction record was not added - User's Prediction record already exists "
							+ "for Keys:" + p_prediction.getUserId() + " and " + p_prediction.getGameId());
				}
			}
			else {
				this.m_logger.warning("Cannot add User's Prediction record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a User's Prediction record for Key: " + p_prediction.getUserId() + 
					" and " + p_prediction.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single user's Prediction record in the database
	 * @param p_prediction
	 * @return
	 */
	public boolean updateData(Prediction p_prediction) {  
		try {
			if(p_prediction != null && StringUtils.isNotEmpty(p_prediction.getUserId()) && 
					StringUtils.isNotEmpty(p_prediction.getGameId() )) {
				//Ensure record exists for that userId & gameId already
				Prediction predictionExists = this.findById(p_prediction.getUserId(), p_prediction.getGameId());
				if( predictionExists != null ) {
					int rowCount = this.update(p_prediction);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Prediction record was not updated successfully for Key:"  + p_prediction.getUserId() + 
								" and " + p_prediction.getGameId());
				}
				else 
					this.m_logger.warning("User's Prediction record was not updated - User record does NOT exists for Key:"  + p_prediction.getUserId() + 
							" and " + p_prediction.getGameId());
			}
			else {
				this.m_logger.warning("Cannot update User's Prediction record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a User's Prediction record for Key: "  + p_prediction.getUserId() + 
					" and " + p_prediction.getGameId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove a user's Predictions records from the database
	 * @param p_userId
	 * @return
	 */
	public boolean deleteUserPredictionData(String p_userId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				//Check if record exists before attempting to delete it
				List<Prediction> predictionExists = this.findByUserId(p_userId);
				if( predictionExists != null && predictionExists.size() > 0) {
					for( Prediction prediction : predictionExists ) {
						int rowCount = this.delete(prediction);
						if(rowCount != -1)
							return true;	
						else
							this.m_logger.warning("User's Predictions record was not deleted for Key:" + prediction.getUserId() 
									+ " and " + prediction.getGameId());
					}
				}
			}
			else {
				this.m_logger.warning("Cannot delete User's Predictions record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete User's Predictions record for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Remove a user's Predictions record from the database
	 * @param p_paymentId
	 * @return
	 */
	public boolean deleteData(String p_userId, String p_predictionId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId) && StringUtils.isNotEmpty(p_predictionId )) {
				//Check if record exists before attempting to delete it
				Prediction predictionExists = this.findById(p_userId, p_predictionId);
				if( predictionExists != null ) {
					int rowCount = this.delete(predictionExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Prediction record was not deleted for Key:" + p_userId +" and "+ p_predictionId);
			
				}
			}
			else {
				this.m_logger.warning("Cannot delete User's Prediction record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete User's Prediction record for Key:" + p_userId +" and "+ p_predictionId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 
	
	/**
	 * 1. One for correct result (team1win/team2win/draw)
	 * 2. Two for each score you get correct
	 * 3. Max Total can be 5
	 */
	public double calcuateUserPredictionScore(List<Fixture> p_fixtures, List<Prediction> p_predictions ) {
		int score = 0;
		try {
			if(p_fixtures!= null && p_fixtures.size() != 0) {
				for(Fixture fixture : p_fixtures) {
					if(p_predictions!= null && p_predictions.size() != 0) {
						for(Prediction prediction : p_predictions) {
							if(StringUtils.isNotEmpty(fixture.getGameId()) && StringUtils.isNotEmpty(prediction.getGameId())) {
								if(StringUtils.equalsIgnoreCase(fixture.getGameId(), prediction.getGameId())) {
									if(fixture.isGamePlayed() ) {
										//1. One for correct result (team1win/team2win/draw)										
										if(StringUtils.equalsIgnoreCase(fixture.getWinningTeam(), prediction.getWinningTeamPrediction()))  {
											if(fixture.whichTeamWon() == prediction.whichTeamToWin())   {												
												score++;
											}
										}
										
										//2. Two for each score you get correct
										if(fixture.getTeamOneScore() == prediction.getTeam1Prediction() ) {
											score = score + 2;
										}
										if(fixture.getTeamTwoScore() == prediction.getTeam2Prediction() ) {
											score = score + 2;
										}
									}
									break;
								}							
							}		
						}
					}
				}
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to calculate User's Prediction score for User:" + p_predictions.get(0).getUserId() );
			this.m_logger.severe(e.getLocalizedMessage());
			return -1;
		}
		return score;
	}
}
