package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Accounts;
import com.crowds.database.sql.Sql;

public class AccountsDao extends AbstractDaoJdbc<Accounts>{

	public Logger			m_logger	= 	Logger.getLogger(AccountsDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Accounts";
	protected static final String	SQL_TABLE_COLUMNS 	= "Account_ID, Money_Received, Prize_Money_Owed, Other_Expenses, TimeStamp";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEY = "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Account_ID=?";
	
	protected static final String	SQL_SELECT_MOSTRECENT= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " ORDER BY TimeStamp DESC LIMIT 1";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Money_Received=?, Prize_Money_Owed=?, Other_Expenses=?, TimeStamp=? WHERE Account_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Account_ID=?";
	
	
	public AccountsDao() {
		super();
	}
	
	
	/**
	 * Find all of the Accounts in the Accounts database table
	 * 
	 * @return list of Accounts
	 */
	protected List<Accounts> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Accounts> accounts = this.findAll(l_sql, new AccountsRowMapper());
			return accounts;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Accounts record for given unique account Id
	 * @param p_accountId
	 * @return Accounts record
	 */
	protected Accounts findById(String p_accountId) {
		try {
			if( StringUtils.isNotBlank(p_accountId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY, new Object[] {p_accountId});
				Accounts account = this.findById(l_sql, new AccountsRowMapper());
				return account;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Find the most up to date Accounts record
	 * @return
	 */
	protected Accounts findMostRecent() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_MOSTRECENT, new Object[] {});
			Accounts account = this.findById(l_sql, new AccountsRowMapper());
			return account;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;		
	}

	
	/**
	 * Insert a new Accounts record into the database
	 * @param p_accounts
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert( Accounts p_accounts ) {
		try {
			if( StringUtils.isNotBlank(p_accounts.getAccountId())) {
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_accounts.getAccountId(), p_accounts.getMoneyReceived(), 
						p_accounts.getPrizeMoneyOwed(), p_accounts.getOtherExpenses(), this.getSdf().format(new Date())});
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
	 * Update a Accounts record in the database
	 * @param p_accounts
	 * @return number of rows affected (-1 if error)
	 */
	protected int update( Accounts p_accounts ) {
		try {
			if( StringUtils.isNotBlank(p_accounts.getAccountId())) {
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_accounts.getMoneyReceived(), p_accounts.getPrizeMoneyOwed(), p_accounts.getOtherExpenses(),
						this.getSdf().format(new Date()), p_accounts.getAccountId()});
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
	 * Remove a Accounts record from the database
	 * @param p_accounts
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete( Accounts p_accounts ) {
		try {
			if( StringUtils.isNotBlank(p_accounts.getAccountId())) {
				Sql l_sql = new Sql(SQL_DELETE, new Object[] {p_accounts.getAccountId()});
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
	public AccountsRowMapper newRowMapper() {
		return new AccountsRowMapper();
	}
	
	/** 
	 * Map the ResultSet to AccountsRowMapper Dto
	 *
	 */
	protected class AccountsRowMapper implements ParameterizedRowMapper<Accounts> {
		
		public AccountsRowMapper() {
			super();
		}
		
		public Accounts mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Accounts dto = new Accounts();
			try {
				dto.setAccountId(rs.getString(seqn++));
				dto.setMoneyReceived(rs.getDouble(seqn++));
				dto.setPrizeMoneyOwed(rs.getDouble(seqn++));
				dto.setOtherExpenses(rs.getDouble(seqn++));
				dto.setDate(getDate(rs.getDate(seqn++)));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
