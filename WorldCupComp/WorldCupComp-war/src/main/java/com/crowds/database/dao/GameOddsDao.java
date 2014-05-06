package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.GameOdds;
import com.crowds.database.sql.Sql;

public class GameOddsDao extends AbstractDaoJdbc<GameOdds>{

	public Logger			m_logger	= 	Logger.getLogger(GameOddsDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Game_Odds";
	protected static final String	SQL_TABLE_COLUMNS 	= "Game_ID, Team1_Odds, Team2_Odds, Draw_Odds, TimeStampEntered";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Game_ID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Team1_Odds=?, Team2_Odds=?, Draw_Odds=?, TimeStampEntered=? WHERE Game_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Game_ID=?";
	
	
	public GameOddsDao() {
		super();
	}
	
	
	/**
	 * Find all of the GameOdds in the GameOdds database table
	 * 
	 * @return list of GameOdds
	 */
	protected List<GameOdds> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<GameOdds> gameOdds = this.findAll(l_sql, new GameOddsRowMapper());
			return gameOdds;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single GameOdds record for given unique gameId
	 * @param p_gameId
	 * @return GameOdds record
	 */
	protected GameOdds findById(String p_gameId) {
		try {
			if( StringUtils.isNotBlank(p_gameId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_gameId});
				GameOdds gameOdds = this.findById(l_sql, new GameOddsRowMapper());
				return gameOdds;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Insert a new GameOdds record into the database
	 * @param p_gameOdds
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(GameOdds p_gameOdds) {
		try {
			if( StringUtils.isNotBlank(p_gameOdds.getGameId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_gameOdds.getGameId(), p_gameOdds.getTeamOneOdds(), p_gameOdds.getTeamTwoOdds(), 
						p_gameOdds.getDrawOdds(), this.getSdf().format(new Date())});
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
	 * Update a GameOdds record in the database
	 * @param p_gameOdds
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(GameOdds p_gameOdds) {
		try {
			if( StringUtils.isNotBlank(p_gameOdds.getGameId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_gameOdds.getTeamOneOdds(), p_gameOdds.getTeamTwoOdds(), p_gameOdds.getDrawOdds(),
										this.getSdf().format(new Date()), p_gameOdds.getGameId()});
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
	 * Remove a GameOdds record from the database
	 * @param p_gameOdds
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(GameOdds p_gameOdds) {
		try {
			if( StringUtils.isNotBlank(p_gameOdds.getGameId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_gameOdds.getGameId()});
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
	public GameOddsRowMapper newRowMapper() {
		return new GameOddsRowMapper();
	}
	
	/** 
	 * Map the ResultSet to GameOdds Dto
	 *
	 */
	protected class GameOddsRowMapper implements ParameterizedRowMapper<GameOdds> {
		
		public GameOddsRowMapper() {
			super();
		}
		
		public GameOdds mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			GameOdds dto = new GameOdds();
			try {
				dto.setGameId(rs.getString(seqn++));
				dto.setTeamOneOdds(rs.getDouble(seqn++));
				dto.setTeamTwoOdds(rs.getDouble(seqn++));
				dto.setDrawOdds(rs.getDouble(seqn++));
				dto.setDate(getDate(rs.getDate(seqn++)));			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
