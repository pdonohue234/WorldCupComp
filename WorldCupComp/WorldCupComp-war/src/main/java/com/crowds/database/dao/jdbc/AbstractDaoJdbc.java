/**
 * AbstractDto - Data Access Object is the meta implementation for all Base Dao's.
 * 
 * A data access object is used to connect to the database. 
 * It handles the flow to and from the table(s) to Dao represents.
 * Contains all the SQL information about the table. 
 * It is basically a table definition, a Dao = Database Table Schema
 * 
 */
package com.crowds.database.dao.jdbc;

import com.crowds.database.sql.Sql;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public abstract class AbstractDaoJdbc<T> {
	
	@Autowired
	protected DataSource 	m_dataSource;

	SimpleDateFormat		m_sdf;
	private Logger			m_logger	= 	Logger.getLogger(AbstractDaoJdbc.class.getName());
	
	public AbstractDaoJdbc() {
		super();
		this.m_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	
	/**
	 * Find all of the Dto's for given sql table
	 * 
	 * @param p_sql
	 * @param p_rowMapper
	 * @return
	 */
	protected List<T> findAll(Sql p_sql, ParameterizedRowMapper<T> p_rowMapper) {
		List<T> l_list = null;
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(m_dataSource);
			List<T> results = jdbcTemplate.query(p_sql.getSql(), p_rowMapper);
			return results;
		}
		catch( Exception e ) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		
		return l_list;
	}
	
	/**
	 * Find a list of Dto's for given sql query
	 * 
	 * @param p_sql
	 * @param p_rowMapper
	 * @return
	 */
	protected List<T> find(Sql p_sql, ParameterizedRowMapper<T> p_rowMapper) {
		List<T> l_list = null;
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(m_dataSource);
			List<T> results = jdbcTemplate.query(p_sql.getSql(), p_sql.getParameters(), p_rowMapper);
			return results;
		}
		catch( Exception e ) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		
		return l_list;
	}
	
	
	/**
	 * Find a single Dto for given sql query with unique row ID
	 * 
	 * @param p_sql
	 * @param p_rowMapper
	 * @return
	 */
	protected T findById(Sql p_sql, ParameterizedRowMapper<T> p_rowMapper) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(m_dataSource);
			List<T> result = jdbcTemplate.query(p_sql.getSql(), p_sql.getParameters(), p_rowMapper);
			
			if( result.isEmpty() ) 
				return null;
			else if( result.size() >= 1 )
				return result.get(0);
			else
				return null;
		}
		catch( Exception e ) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	protected int getNumRows(Sql p_sql) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(m_dataSource);
			int numRows = jdbcTemplate.queryForInt(p_sql.getSql(), p_sql.getParameters());
			
			return numRows;
		}
		catch( Exception e ) {
			this.m_logger.severe(e.getLocalizedMessage());
			return -1;
		}		
	}
	
	/**
	 * Execute an SQL insert/update or delete statement
	 * 
	 * @param p_sql
	 * @return num of rows inserted (or -1 if error occurred)
	 */
	protected int update(Sql p_sql) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(m_dataSource);
			int rows = jdbcTemplate.update(p_sql.getSql(), p_sql.getParameters());
			this.m_logger.warning("Record row num affected: " + rows);
			return rows;
		}
		catch( Exception e ) {
			this.m_logger.severe(e.getLocalizedMessage());
			return -1;
		}		
	}
	
	/**
	 * Create row mapper for the Dao
	 * @param dto
	 * @return
	 */
	public abstract ParameterizedRowMapper<T> newRowMapper();
	
	
	/**
	 * java.sql.Date corresponds to SQL DATE which means it stores years, months and days 
	 * while hour, minute, second and millisecond are ignored. Additionally sql.Date isn't tied to timezones.
	 * 
	 * @param p_dbDate
	 * @return
	 */
	public Date getDate( java.sql.Date p_dbDate)  {
		if(p_dbDate == null)
			return null;
		return new java.util.Date(p_dbDate.getTime()); //Will not have time set
	}


	public final SimpleDateFormat getSdf() {
		return m_sdf;
	}


	public final void setSdf(SimpleDateFormat p_sdf) {
		m_sdf = p_sdf;
	}
	
}
