package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Prediction;
import com.crowds.database.sql.Sql;

public class PredictionDao extends AbstractDaoJdbc<Prediction>{

	public Logger			m_logger	= 	Logger.getLogger(PredictionDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Predictions";
	protected static final String	SQL_TABLE_COLUMNS 	= "User_ID, Game_ID, Team1_Prediction, Team2_Prediction, Result_Prediction, Time_Stamp";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEYS= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE User_ID=? AND Game_ID=?";
	protected static final String	SQL_SELECT_USINGKEY1= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE User_ID=? ";
	protected static final String	SQL_SELECT_USINGKEY2= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Game_ID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?, ?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Team1_Prediction=?, Team2_Prediction=?, Result_Prediction=?, Time_Stamp=? WHERE User_ID=? AND Game_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE User_ID=? AND Game_ID=?";
	
	
	public PredictionDao() {
		super();
	}
	
	
	/**
	 * Find all of the Predictions in the Prediction database table
	 * 
	 * @return list of Predictions
	 */
	protected List<Prediction> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Prediction> predictions = this.findAll(l_sql, new PredictionRowMapper());
			return predictions;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Prediction record for given unique user ID & game ID
	 * @param p_userId
	 * @param game ID
	 * @return Prediction record
	 */
	protected Prediction findById(String p_userId, String p_gameId) {
		try {
			if( StringUtils.isNotBlank(p_userId) && StringUtils.isNotBlank(p_gameId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEYS, new Object[] {p_userId, p_gameId});
				Prediction prediction = this.findById(l_sql, new PredictionRowMapper());
				return prediction;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Find a list of Predictions record for given unique user ID 
	 * @param p_userId
	 * @return List of Prediction records
	 */
	protected List<Prediction> findByUserId(String p_userId) {
		try {
			if( StringUtils.isNotBlank(p_userId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY1, new Object[] {p_userId});
				List<Prediction> predictions = this.find(l_sql, new PredictionRowMapper());
				return predictions;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}	
	
	/**
	 * Find a list of Predictions record for given unique game ID 
	 * @param p_gameId
	 * @return List of Prediction records
	 */
	protected List<Prediction> findByGameId(String p_gameId) {
		try {
			if( StringUtils.isNotBlank(p_gameId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY2, new Object[] {p_gameId});
				List<Prediction> predictions = this.find(l_sql, new PredictionRowMapper());
				return predictions;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}	
	
	/**
	 * Insert a new Prediction record into the database
	 * @param p_prediction
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(Prediction p_prediction) {
		try {
			if( StringUtils.isNotBlank(p_prediction.getUserId()) && StringUtils.isNotBlank(p_prediction.getGameId())) {
				validateFields( p_prediction );
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_prediction.getUserId(), p_prediction.getGameId(), p_prediction.getTeam1Prediction(), 
						p_prediction.getTeam2Prediction(), p_prediction.getWinningTeamPrediction(),
						this.getSdf().format(new Date())});
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
	 * Update a Prediction record in the database
	 * @param p_prediction
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(Prediction p_prediction) {
		try {
			if( StringUtils.isNotBlank(p_prediction.getUserId()) && StringUtils.isNotBlank(p_prediction.getGameId())) {
				validateFields(p_prediction );
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_prediction.getTeam1Prediction(), p_prediction.getTeam2Prediction(), 
						p_prediction.getWinningTeamPrediction(), this.getSdf().format(new Date()),
						p_prediction.getUserId(), p_prediction.getGameId()});
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
	 * Remove a Prediction record from the database
	 * @param p_prediction
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(Prediction p_prediction) {
		try {
			if( StringUtils.isNotBlank(p_prediction.getUserId()) && StringUtils.isNotBlank(p_prediction.getGameId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_prediction.getUserId(), p_prediction.getGameId()});
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
	 * Private method used to ensure there are no NULLs before persisting Object to Database Table
	 * @param p_prediction
	 */
	private void validateFields( Prediction p_prediction ) {
	
	}
	
	@Override
	public PredictionRowMapper newRowMapper() {
		return new PredictionRowMapper();
	}
	
	/** 
	 * Map the ResultSet to Prediction Dto
	 *
	 */
	protected class PredictionRowMapper implements ParameterizedRowMapper<Prediction> {
		
		public PredictionRowMapper() {
			super();
		}
		
		public Prediction mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Prediction dto = new Prediction();
			try {
				dto.setUserId(rs.getString(seqn++));
				dto.setGameId(rs.getString(seqn++));
				dto.setTeam1Prediction(rs.getInt(seqn++));
				dto.setTeam2Prediction(rs.getInt(seqn++));
				dto.setWinningTeamPrediction(rs.getString(seqn++));
				dto.setDate(getDate(rs.getDate(seqn++)));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
