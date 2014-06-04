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
	protected static final String	SQL_TABLE_COLUMNS 	= "User_Id, Password, Trans_ID, Name, Date_Registered, Private_Comp_Name";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY	= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE User_Id=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Password=?, Trans_ID=?, Name=?, Date_Registered=?, Private_Comp_Name=? WHERE User_Id=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE User_Id=?";
	
	protected static final String	SQL_PRIVATE_COMP_EXISTS	= "SELECT count(1) FROM " + SQL_TABLE_NAME + " WHERE Private_Comp_Name=?";
	
	protected static final String	SQL_PRIVATE_COMP_LIST	= "SELECT * FROM " + SQL_TABLE_NAME + " WHERE Private_Comp_Name=? ORDER BY Name";
			
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
	 * Find number of rows that exist with a specific Private_Comp_Name specified.
	 * @param p_privateCompName
	 * @return User record
	 */
	protected int findByPrivateCompName(String p_privateCompName) {
		try {
			if( StringUtils.isNotBlank(p_privateCompName)) {
				Sql l_sql = new Sql(SQL_PRIVATE_COMP_EXISTS, new Object[] {p_privateCompName});
				int numRows = this.getNumRows(l_sql);
				return numRows;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return -1;
	}	
	
	/**
	 * Find all of the Users in the User database table by p_privateCompName
	 * 
	 * @return list of Users
	 */
	protected List<User> findListByPrivateCompName(String p_privateCompName) {
		try {
			Sql l_sql = new Sql(SQL_PRIVATE_COMP_LIST, new Object[] {p_privateCompName});
			List<User> users = this.find(l_sql, new UserRowMapper());
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
				if(user != null)
					this.m_logger.warning("Found: " + p_userId);
				return user;
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
						new Object[] {p_user.getUserId(), p_user.getPassword(), p_user.getTransId(), p_user.getName(), this.getSdf().format(new Date()), p_user.getPrivateCompName()});

				this.m_logger.warning(SQL_ADD);
				this.m_logger.warning(p_user.getUserId()+", "+p_user.getPassword()+", "+p_user.getTransId()+", "+p_user.getName()+", "+this.getSdf().format(new Date())+", "+p_user.getPrivateCompName());
				
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
						new Object[] {p_user.getPassword(), p_user.getTransId(), p_user.getName(), this.getSdf().format(new Date()), p_user.getPrivateCompName(), p_user.getUserId()});
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
		p_user.setTransId(( p_user.getTransId() == null ? "" : p_user.getTransId() ));
		p_user.setName(( p_user.getName() == null ? "" : p_user.getName() ));
		p_user.setPrivateCompName(( p_user.getPrivateCompName() == null ? "" : p_user.getPrivateCompName() ));
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
				dto.setTransId(rs.getString(seqn++));
				dto.setName(rs.getString(seqn++));
				dto.setDateRegistered(getDate(rs.getDate(seqn++)));
				dto.setPrivateCompName(rs.getString(seqn++));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
