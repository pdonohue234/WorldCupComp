package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Fixture;
import com.crowds.database.sql.Sql;

public class FixtureDao extends AbstractDaoJdbc<Fixture>{

	public Logger			m_logger	= 	Logger.getLogger(FixtureDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "FIXTURE";
	protected static final String	SQL_TABLE_COLUMNS 	= "GAMEID, EVENTID, GAME_TITLE, GAME_DATE, GAME_TIME, TEAM1, TEAM2, ROUND, LOCATION";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEYS= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE GAMEID=? AND EVENTID=?";
	protected static final String	SQL_SELECT_USINGKEY1= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE GAMEID=? ";
	protected static final String	SQL_SELECT_USINGKEY2= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE EVENTID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET GAME_TITLE=?, GAME_DATE=?, GAME_TIME=?, TEAM1=?, TEAM2=?, ROUND=?, LOCATION=? WHERE GAMEID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE GAMEID=?";
	
	
	public FixtureDao() {
		super();
	}
	
	
	/**
	 * Find all of the Fixtures in the Fixture database table
	 * 
	 * @return list of Fixtures
	 */
	protected List<Fixture> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Fixture> fixtures = this.findAll(l_sql, new FixtureRowMapper());
			return fixtures;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Fixture record for given unique gameId and eventId
	 * @param p_gameId 
	 * @param p_eventId
	 * @return Fixture record
	 */
	protected Fixture findById(String p_gameId, String p_eventId) {
		try {
			if( StringUtils.isNotBlank(p_gameId) && StringUtils.isNotBlank(p_eventId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEYS, new Object[] {p_gameId, p_eventId});
				Fixture fixtures = this.findById(l_sql, new FixtureRowMapper());
				return fixtures;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Find a single Fixture record for given unique gameId 
	 * @param p_gameId
	 * @return Fixture record
	 */
	protected Fixture findById(String p_gameId) {
		try {
			if( StringUtils.isNotBlank(p_gameId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY1, new Object[] {p_gameId});
				Fixture fixtures = this.findById(l_sql, new FixtureRowMapper());
				return fixtures;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}	
		
	
	/**
	 * Find a list of Fixtures record for given unique event ID 
	 * @param p_eventId
	 * @return List of Fixtures records
	 */
	protected List<Fixture> findByEventId(String p_eventId) {
		try {
			if( StringUtils.isNotBlank(p_eventId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY2, new Object[] {p_eventId});
				List<Fixture> fixtures = this.findAll(l_sql, new FixtureRowMapper());
				return fixtures;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}	
	
	/**
	 * Insert a new Fixture record into the database
	 * @param p_fixtures
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(Fixture p_fixtures) {
		try {
			if( StringUtils.isNotBlank(p_fixtures.getGameId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_fixtures.getGameId(), p_fixtures.getGameTitle(), p_fixtures.getGameDate(), 
						p_fixtures.getGameTitle(), p_fixtures.getTeamOne(), p_fixtures.getTeamTwo(), p_fixtures.getLocation()});
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
	 * Update a Fixture record in the database
	 * @param p_fixtures
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(Fixture p_fixtures) {
		try {
			if( StringUtils.isNotBlank(p_fixtures.getGameId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_fixtures.getGameTitle(), p_fixtures.getGameDate(), p_fixtures.getGameTitle(),
						p_fixtures.getTeamOne(), p_fixtures.getTeamTwo(), p_fixtures.getLocation(), p_fixtures.getGameId()});
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
	 * Remove a Fixture record from the database
	 * @param p_fixtures
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(Fixture p_fixtures) {
		try {
			if( StringUtils.isNotBlank(p_fixtures.getGameId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_fixtures.getGameId()});
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
	public FixtureRowMapper newRowMapper() {
		return new FixtureRowMapper();
	}
	
	/** 
	 * Map the ResultSet to Fixture Dto
	 *
	 */
	protected class FixtureRowMapper implements ParameterizedRowMapper<Fixture> {
		
		public FixtureRowMapper() {
			super();
		}
		
		public Fixture mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Fixture dto = new Fixture();
			try {
				dto.setGameId(rs.getString(seqn++));
				dto.setGameTitle(rs.getString(seqn++));
				dto.setGameDate(getDate(rs.getDate(seqn++)));
				dto.setGameTime(rs.getInt(seqn++));
				dto.setTeamOne(rs.getString(seqn++));
				dto.setTeamTwo(rs.getString(seqn++));
				dto.setRound(rs.getString(seqn++));
				dto.setLocation(rs.getString(seqn++));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
