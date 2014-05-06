package com.crowds.services;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.AccountsDao;
import com.crowds.database.dto.Accounts;

public class AccountService extends AccountsDao {
	
	/** 
	 * Find list of all Accounts in system
	 * 
	 * @return list of Accounts
	 */
	public List<Accounts> getAccountList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Accounts records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Account based on the accountId
	 * @param accountId
	 * @return
	 */
	public Accounts getAccounts(String accountId) { 
		try {
			if( StringUtils.isNotBlank(accountId)) {
				return this.findById(accountId); 
			}
			else {
				this.m_logger.warning("Cannot find Account record because of empty Keys passed!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find Account record for Keys: " + accountId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	
	/**
	 * Find the most recent Account record
	 * @param accountId
	 * @return
	 */
	public Accounts findMostRecent() { 
		try {
			return super.findMostRecent();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find most recent Account record.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
		
	
	/** 
	 * Insert a single Account record into the database
	 * @param p_account
	 * @return
	 */
	public boolean insertData(Accounts p_account) {  
		try {
			if(p_account != null && StringUtils.isNotEmpty(p_account.getAccountId()) ) {
				//Check if record exists for that id already
				Accounts accountExists = this.findById(p_account.getAccountId());
				if( accountExists == null ) {
					int rowCount = this.insert(p_account);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Accounts records were not added successfully for Key:" + p_account.getAccountId());
				}
				else {
					this.m_logger.warning("Accounts records were not added - records already exists "
							+ "for Key:" + p_account.getAccountId());
				}
			}
			else {
				this.m_logger.warning("Cannot add Accounts record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create an Accounts record for Key: " + p_account.getAccountId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single Accounts record in the database
	 * @param p_account
	 * @return
	 */
	public boolean updateData(Accounts p_account) {  
		try {
			if(p_account != null && StringUtils.isNotEmpty(p_account.getAccountId()) ) {
				//Ensure record exists for that id already
				Accounts accountExists = this.findById(p_account.getAccountId());
				if( accountExists != null ) {
					int rowCount = this.update(p_account);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Accounts record was not updated successfully for Key:"  + p_account.getAccountId());
				}
				else 
					this.m_logger.warning("Accounts record was not updated - record does NOT exists for Key:"  + p_account.getAccountId());
			}
			else {
				this.m_logger.warning("Cannot update Accounts record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update an Accounts record for Key: "  + p_account.getAccountId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	
 
	
	/** 
	 * Remove an Accounts record from the database
	 * @param p_accountId
	 * @return
	 */
	public boolean deleteData(String p_accountId) {  
		try {
			if(StringUtils.isNotEmpty(p_accountId)) {
				//Check if record exists before attempting to delete it
				Accounts accountExists = this.findById(p_accountId);
				if( accountExists != null ) {
					int rowCount = this.delete(accountExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("Accounts record was not deleted for Key:" + p_accountId);
			
				}
			}
			else {
				this.m_logger.warning("Cannot delete Accounts record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete Accounts record for Key:" + p_accountId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	
}
