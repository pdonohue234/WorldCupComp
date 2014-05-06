package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.UserScore;
import com.crowds.database.sql.Sql;

public class UserScoreDao extends AbstractDaoJdbc<UserScore>{

	public Logger			m_logger	= 	Logger.getLogger(UserScoreDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "USERSCORE";
	protected static final String	SQL_TABLE_COLUMNS 	= "USERID, NUM_PREDICTIONS_MADE, NUM_PREDICTIONS_CORRECT, NUM_PREDICTIONS_INCORRECT, NUM_PREDICTIONS_PENDING, CURRENT_SCORE";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE USERID=?";
	protected static final String	SQL_SELECT_TOP		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " ORDER BY CURRENT_SCORE ASC LIMIT ?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET NUM_PREDICTIONS_MADE=?, NUM_PREDICTIONS_CORRECT=?, NUM_PREDICTIONS_INCORRECT=?, NUM_PREDICTIONS_PENDING=?, CURRENT_SCORE=? WHERE USERID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE USERID=?";
	
	
	public UserScoreDao() {
		super();
	}
	
	
	/**
	 * Find all of the UserScores in the UserScore database table
	 * 
	 * @return list of UserScores
	 */
	protected List<UserScore> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<UserScore> userScores = this.findAll(l_sql, new UserScoreRowMapper());
			return userScores;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single UserScore record for given unique user ID
	 * @param p_userId
	 * @return UserScore record
	 */
	protected UserScore findById(String p_userId) {
		try {
			if( StringUtils.isNotBlank(p_userId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_userId});
				UserScore userScore = this.findById(l_sql, new UserScoreRowMapper());
				return userScore;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	protected List<UserScore> findTopUserScores(int p_num) {
		try {
			Sql l_sql = new Sql(SQL_SELECT_TOP, new Object[] {p_num});
			List<UserScore> userScores = this.findAll(l_sql, new UserScoreRowMapper());
			return userScores;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}		
	}
	
	/**
	 * Insert a new UserScore record into the database
	 * @param p_userScore
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(UserScore p_userScore) {
		try {
			if( StringUtils.isNotBlank(p_userScore.getUserId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_userScore.getUserId(), p_userScore.getNumPredictionsMade(), p_userScore.getNumPredictionsCorrect(),
							p_userScore.getNumPredictionsIncorrect(), p_userScore.getNumPredictionsPending(), p_userScore.getCurrentScore()});
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
	 * Update a UserScore record in the database
	 * @param p_userScore
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(UserScore p_userScore) {
		try {
			if( StringUtils.isNotBlank(p_userScore.getUserId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_userScore.getNumPredictionsMade(), p_userScore.getNumPredictionsCorrect(), p_userScore.getNumPredictionsIncorrect(), 
							p_userScore.getNumPredictionsPending(), p_userScore.getCurrentScore(), p_userScore.getUserId()});
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
	 * Remove a UserScore record from the database
	 * @param p_userScore
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(UserScore p_userScore) {
		try {
			if( StringUtils.isNotBlank(p_userScore.getUserId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_userScore.getUserId()});
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
	public UserScoreRowMapper newRowMapper() {
		return new UserScoreRowMapper();
	}
	
	/** 
	 * Map the ResultSet to User Dto
	 *
	 */
	protected class UserScoreRowMapper implements ParameterizedRowMapper<UserScore> {
		
		public UserScoreRowMapper() {
			super();
		}
		
		public UserScore mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			UserScore dto = new UserScore();
			try {
				dto.setUserId(rs.getString(seqn++));
				dto.setNumPredictionsMade(rs.getInt(seqn++));
				dto.setNumPredictionsCorrect(rs.getInt(seqn++));
				dto.setNumPredictionsIncorrect(rs.getInt(seqn++));
				dto.setNumPredictionsPending(rs.getInt(seqn++));
				dto.setCurrentScore(rs.getInt(seqn++));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
