package com.crowds.services;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.UserDao;
import com.crowds.database.dto.User;

public class UserService extends UserDao {
	
	public Logger			m_logger	= 	Logger.getLogger(UserService.class.getName());
	
	/** 
	 * Find list of all users in system
	 * 
	 * @return list of users
	 */
	public List<User> getUserList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL User records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single user based on their userId
	 * @param id
	 * @return
	 */
	public User getUser(String p_userId) { 
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				return this.findById(p_userId); 
			}
			else {
				this.m_logger.warning("Cannot find a User record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User record for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find and validate a single user based on their userId and password
	 * @param id
	 * @return
	 */
	public User validateUser(User p_user) { 
		try {
			if(StringUtils.isNotEmpty(p_user.getUserId()) && StringUtils.isNotEmpty(p_user.getPassword()) ){
				User dbUser = this.getUser(p_user.getUserId()); 
				
				if(dbUser != null) {
					if(StringUtils.equals(p_user.getPassword(), dbUser.getPassword())) {
						this.m_logger.warning("User exists & is validated - userId: " + p_user.getUserId());
					}
					else {
						this.m_logger.warning("User exists but invalid password for userId: " + p_user.getUserId());
					}
				}
				else {
					this.m_logger.warning("User does not exist for userId: " + p_user.getUserId());
				}
			}
			else {
				this.m_logger.warning("Cannot find a User record for empty credentials!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find & validate User record for User Id: " + p_user.getUserId());
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find a list of users based on email address
	 * @param id
	 * @return
	 */
	public List<User> getUsersByEmail(String p_email) { 
		try {
			if(StringUtils.isNotEmpty(p_email)) {
				return this.findByEmail(p_email); 
			}
			else {
				this.m_logger.warning("Cannot find User records based on empty Email!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User record with Email: " + p_email);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single user record into the database
	 * @param p_user
	 * @return
	 */
	public boolean insertData(User p_user) {  
		try {
			if(p_user != null && StringUtils.isNotEmpty(p_user.getUserId())) {
				this.m_logger.warning("Checking User does not already exist!");
				//Check if record exists for that userId already
				User userExists = this.findById(p_user.getUserId());
				if( userExists == null ) {
					this.m_logger.warning("User does not already exist - so will be added");
					int rowCount = this.insert(p_user);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User record was not added successfully for Key:" + p_user.getUserId());
				}
				else {
					this.m_logger.warning("User record was not added - User record already exists for Key:" + p_user.getUserId());
				}
			}
			else {
				this.m_logger.warning("Cannot add User record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a User record for Key: " + p_user.getUserId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single user record in the database
	 * @param p_user
	 * @return
	 */
	public boolean updateData(User p_user) {  
		try {
			if(p_user != null && StringUtils.isNotEmpty(p_user.getUserId())) {
				//Ensure record exists for that userId already
				User userExists = this.findById(p_user.getUserId());
				if( userExists != null ) {
					int rowCount = this.update(p_user);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User record was not updated successfully for Key:" + p_user.getUserId());
				}
				else {
					this.m_logger.warning("User record was not updated - User record does NOT exists for Key:" + p_user.getUserId());
				}
			}
			else {
				this.m_logger.warning("Cannot update User record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a User record for Key: " + p_user.getUserId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove a user record from the database
	 * @param p_userId
	 * @return
	 */
	public boolean deleteData(String p_userId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				//Check if record exists before attempting to delete it
				User userExists = this.findById(p_userId);
				if( userExists != null ) {
					int rowCount = this.delete(userExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User record was not deleted for Key:" + p_userId);
				}
			}
			else {
				this.m_logger.warning("Cannot delete User record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete User record for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}   
}
