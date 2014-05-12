package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Team;
import com.crowds.database.sql.Sql;

public class TeamDao extends AbstractDaoJdbc<Team>{

	public Logger			m_logger	= 	Logger.getLogger(EventDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Teams";
	protected static final String	SQL_TABLE_COLUMNS 	= "Team_ID, Event_ID, Team_Name";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Team_ID=? and Event_ID=?";
	protected static final String	SQL_SELECT_USINGEVENT= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Event_ID=?";

	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Team_Name=? WHERE Team_ID=? and Event_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Team_ID=? and Event_ID=?";
	
	
	public TeamDao() {
		super();
	}
	
	
	/**
	 * Find all of the Teams in the Teams database table
	 * 
	 * @return list of Teams
	 */
	protected List<Team> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Team> teams = this.findAll(l_sql, new TeamRowMapper());
			return teams;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Team record for given unique teamId and eventId
	 * @param p_teamId
	 * @param p_eventId
	 * @return Event record
	 */
	protected Team findById(String p_teamId, String p_eventId) {
		try {
			if( StringUtils.isNotBlank(p_teamId) && StringUtils.isNotBlank(p_eventId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_teamId, p_eventId});
				Team team = this.findById(l_sql, new TeamRowMapper());
				return team;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	/**
	 * Find all Team records for given event
	 * @param p_eventId
	 * @return Event record
	 */
	protected List<Team> findByEvent(String p_eventId){
		try {
			if( StringUtils.isNotBlank(p_eventId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGEVENT, new Object[] {p_eventId});
				List<Team> teams = this.find(l_sql, new TeamRowMapper());
				return teams;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}

	
	/**
	 * Insert a new Team record into the database
	 * @param p_team
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(Team p_team) {
		try {
			if(StringUtils.isNotBlank(p_team.getTeamId()) && StringUtils.isNotBlank(p_team.getEventId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_team.getTeamId(), p_team.getEventId(), p_team.getTeamName()});
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
	 * Update a Team record in the database
	 * @param p_team
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(Team p_team) {
		try {
			if( StringUtils.isNotBlank(p_team.getTeamId()) && StringUtils.isNotBlank(p_team.getEventId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_team.getTeamName(), p_team.getTeamId(), p_team.getEventId()});
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
	 * Remove a Team record from the database
	 * @param p_team
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(Team p_team ) {
		try {
			if( StringUtils.isNotBlank(p_team.getTeamId()) && StringUtils.isNotBlank(p_team.getEventId())) {
				Sql l_sql = new Sql(SQL_DELETE, new Object[] {p_team.getTeamId(), p_team.getEventId()});
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
	public TeamRowMapper newRowMapper() {
		return new TeamRowMapper();
	}
	
	/** 
	 * Map the ResultSet to Team Dto
	 *
	 */
	protected class TeamRowMapper implements ParameterizedRowMapper<Team> {
		
		public TeamRowMapper() {
			super();
		}
		
		public Team mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Team dto = new Team();
			try {
				dto.setTeamId(rs.getString(seqn++));
				dto.setEventId(rs.getString(seqn++));
				dto.setTeamName(rs.getString(seqn++));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
