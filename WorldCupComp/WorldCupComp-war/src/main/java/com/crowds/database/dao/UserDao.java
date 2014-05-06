package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.User;
import com.crowds.database.sql.Sql;

public class UserDao extends AbstractDaoJdbc<User>{

	public Logger			m_logger	= 	Logger.getLogger(UserDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Users";
	protected static final String	SQL_TABLE_COLUMNS 	= "User_Id, Password, Email, Name, Date_Registered";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE User_Id=?";
	protected static final String	SQL_SELECT_USINGEMAIL= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Email=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Password=?, Email=?, Name=?, Date_Registered=? WHERE User_Id=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE User_Id=?";
	
	
	public UserDao() {
		super();
	}
	
	
	/**
	 * Find all of the Users in the User database table
	 * 
	 * @return list of Users
	 */
	protected List<User> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<User> users = this.findAll(l_sql, new UserRowMapper());
			return users;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single User record for given unique user ID
	 * @param p_userId
	 * @return User record
	 */
	protected User findById(String p_userId) {
		try {
			if( StringUtils.isNotBlank(p_userId)) {
				this.m_logger.warning("Finding: " + p_userId);
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_userId});
				User user = this.findById(l_sql, new UserRowMapper());
				return user;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	
	/**
	 * Find all of the Users in the User database table
	 * 
	 * @return list of Users
	 */
	protected List<User> findByEmail(String p_email) {
		try {
			if( StringUtils.isNotBlank(p_email)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGEMAIL, new Object[] {p_email});
				List<User> users = this.find(l_sql, new UserRowMapper());
				return users;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Insert a new User record into the database
	 * @param p_user
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert(User p_user) {
		try {
			if( StringUtils.isNotBlank(p_user.getUserId())) {
				validateFields(p_user );
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_user.getUserId(), p_user.getPassword(), p_user.getEmail(), p_user.getName(), this.getSdf().format(new Date())});
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
	 * Update a User record in the database
	 * @param p_user
	 * @return number of rows affected (-1 if error)
	 */
	protected int update(User p_user) {
		try {
			if( StringUtils.isNotBlank(p_user.getUserId())) {
				validateFields(p_user );
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_user.getPassword(), p_user.getEmail(), p_user.getName(), this.getSdf().format(new Date()), p_user.getUserId()});
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
	 * Remove a User record from the database
	 * @param p_user
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete(User p_user) {
		try {
			if( StringUtils.isNotBlank(p_user.getUserId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_user.getUserId()});
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
	 * @param p_user
	 */
	private void validateFields( User p_user ) {
		p_user.setPassword(( p_user.getPassword() == null ? "" : p_user.getPassword() ));
		p_user.setEmail(( p_user.getEmail() == null ? "" : p_user.getEmail() ));
		p_user.setName(( p_user.getName() == null ? "" : p_user.getName() ));
	}
	
	@Override
	public UserRowMapper newRowMapper() {
		return new UserRowMapper();
	}
	
	/** 
	 * Map the ResultSet to User Dto
	 *
	 */
	protected class UserRowMapper implements ParameterizedRowMapper<User> {
		
		public UserRowMapper() {
			super();
		}
		
		public User mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			User dto = new User();
			try {
				dto.setUserId(rs.getString(seqn++));
				dto.setPassword(rs.getString(seqn++));
				dto.setEmail(rs.getString(seqn++));
				dto.setName(rs.getString(seqn++));
				dto.setDateRegistered(getDate(rs.getDate(seqn++)));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
