package com.crowds.database.dao;

import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Fixture;
import com.crowds.database.sql.Sql;

public class FixtureDao extends AbstractDaoJdbc<Fixture>{

	public Logger			m_logger	= 	Logger.getLogger(FixtureDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Fixtures";
	protected static final String	SQL_TABLE_COLUMNS 	= "Game_ID, Event_ID, Game_Date, Team1, Team1_Score, Team2, Team2_Score, Game_Played, Winning_Team, Round, Game_Location";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEYS= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Game_ID=? AND Event_ID=?";
	protected static final String	SQL_SELECT_USINGKEY1= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Game_ID=? ";
	protected static final String	SQL_SELECT_USINGKEY2= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Event_ID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Game_Date=?, Team1=?, Team1_Score=?, Team2=?, Team2_Score=?, Game_Played=?, Winning_Team=?, Round=?, Game_Location=? WHERE Game_ID=? AND Event_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Game_ID=? AND Event_ID=?";
	
	
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
	protected Fixture findByGameId(String p_gameId) {
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
				List<Fixture> fixtures = this.find(l_sql, new FixtureRowMapper());
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
						new Object[] {p_fixtures.getGameId(), p_fixtures.getEventId(), p_fixtures.getGameDate(), 
						p_fixtures.getTeamOne(), p_fixtures.getTeamOneScore(), p_fixtures.getTeamTwo(), p_fixtures.getTeamTwoScore(), 
						p_fixtures.getGamePlayed(), p_fixtures.getWinningTeam(), p_fixtures.getRound(), p_fixtures.getGameLocation()});
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
						new Object[] {p_fixtures.getGameDate(), p_fixtures.getTeamOne(), p_fixtures.getTeamOneScore(), p_fixtures.getTeamTwo(), 
						p_fixtures.getTeamTwoScore(), p_fixtures.getGamePlayed(), p_fixtures.getWinningTeam(), p_fixtures.getRound(), 
						p_fixtures.getGameLocation(), p_fixtures.getGameId(), p_fixtures.getEventId()});
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
			if( StringUtils.isNotBlank(p_fixtures.getGameId()) && StringUtils.isNotBlank(p_fixtures.getEventId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_fixtures.getGameId(), p_fixtures.getEventId()});
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
				dto.setEventId(rs.getString(seqn++));
				Time t = rs.getTime(seqn);
				Date d = rs.getDate(seqn++);
				dto.setGameDate(getDate(d, t) );
				dto.setTeamOne(rs.getString(seqn++));
				dto.setTeamOneScore(rs.getInt(seqn++));
				dto.setTeamTwo(rs.getString(seqn++));
				dto.setTeamTwoScore(rs.getInt(seqn++));
				dto.setGamePlayed(rs.getString(seqn++));
				dto.setWinningTeam(rs.getString(seqn++));
				dto.setRound(rs.getString(seqn++));
				dto.setGameLocation(rs.getString(seqn++));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
		
		public java.util.Date getDate(Date date, Time time) {
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            Calendar calendar1=Calendar.getInstance();
            calendar1.setTime(time);
            calendar.set(Calendar.MINUTE, calendar1.get(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar1.get(Calendar.SECOND));
            calendar.set(Calendar.HOUR_OF_DAY, calendar1.get(Calendar.HOUR_OF_DAY));
            return calendar.getTime();
        }
	}
}
