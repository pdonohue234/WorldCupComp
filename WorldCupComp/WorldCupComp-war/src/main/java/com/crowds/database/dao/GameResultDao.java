package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.GameResult;
import com.crowds.database.sql.Sql;

public class GameResultDao extends AbstractDaoJdbc<GameResult>{

	public Logger			m_logger	= 	Logger.getLogger(this.getClass());
	
	protected static final String	SQL_TABLE_NAME 		= "GAMERESULT";
	protected static final String	SQL_TABLE_COLUMNS 	= "GAMEID, TEAM1_SCORE, TEAM2_SCORE, WINNING_TEAM";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE GAMEID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET TEAM1_SCORE=?, TEAM2_SCORE=?, WINNING_TEAM=? WHERE GAMEID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE GAMEID=?";
	
	
	public GameResultDao() {
		super();
	}
	
	
	/**
	 * Find all of the GameResults in the GameResult database table
	 * 
	 * @return list of GameResults
	 */
	protected List<GameResult> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<GameResult> gameResults = this.findAll(l_sql, new GameResultRowMapper());
			return gameResults;
		}
		catch(Exception e) {
			this.m_logger.error(e);
			return null;
		}
	}
	
	/**
	 * Find a single GameResult record for given unique gameId
	 * @param p_gameId
	 * @return GameResult record
	 */
	protected GameResult findById(String p_gameId) {
		try {
			if( StringUtils.isNotBlank(p_gameId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_gameId});
				GameResult gameResult = this.findById(l_sql, new GameResultRowMapper());
				return gameResult;
			}
		}
		catch(Exception e) {
			this.m_logger.error(e);
		}
		return null;
	}
	
	/**
	 * Insert a new GameResult record into the database
	 * @param p_gameResult
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(GameResult p_gameResult) {
		try {
			if( StringUtils.isNotBlank(p_gameResult.getGameId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_gameResult.getGameId(), p_gameResult.getTeamOneScore(), 
						p_gameResult.getTeamTwoScore(), p_gameResult.getWinningTeam()});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.error(e);
		}
		return -1;		
	}
	
	/**
	 * Update a GameResult record in the database
	 * @param p_gameResult
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(GameResult p_gameResult) {
		try {
			if( StringUtils.isNotBlank(p_gameResult.getGameId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_gameResult.getTeamOneScore(), p_gameResult.getTeamTwoScore(), 
						p_gameResult.getWinningTeam(), p_gameResult.getGameId()});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.error(e);
		}
		return -1;		
	}	
	
	/**
	 * Remove a GameResult record from the database
	 * @param p_gameResult
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(GameResult p_gameResult) {
		try {
			if( StringUtils.isNotBlank(p_gameResult.getGameId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_gameResult.getGameId()});
				int rows = this.update(l_sql);
				return rows;
			}
		}
		catch(Exception e) {
			this.m_logger.error(e);
		}
		return -1;		
	}	
	
	
	@Override
	public GameResultRowMapper newRowMapper() {
		return new GameResultRowMapper();
	}
	
	/** 
	 * Map the ResultSet to GameResult Dto
	 *
	 */
	protected class GameResultRowMapper implements ParameterizedRowMapper<GameResult> {
		
		public GameResultRowMapper() {
			super();
		}
		
		public GameResult mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			GameResult dto = new GameResult();
			try {
				dto.setGameId(rs.getString(seqn++));
				dto.setTeamOneScore(rs.getInt(seqn++));
				dto.setTeamTwoScore(rs.getInt(seqn++));
				dto.setWinningTeam(rs.getString(seqn++));
			}
			catch(Exception e) {
				m_logger.error(e);
			}
			
			return dto;
		}
	}
}
