package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class User extends Dto<User> implements Serializable {
	
	private static final long serialVersionUID = -12345678901234567L;
	
	public static final String MAPPED_TABLE_NAME = "Users";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"USERID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"PASSWORD", "EMAIL", "NAME", "DATE_REGISTERED"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int USERID = 0;
	
	private String	m_password;
	private String 	m_email;
	private String	m_name;
	private Date	m_dateRegistered;
	
	
	/**
	 * Default Constructor
	 */
	public User() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts UserId
	 */
	public User(String p_userId) {
		super( p_userId );
	}
	
	
	public String getUserId() {
		return (String) m_ids[USERID];
	}
	
	public void setUserId( String p_userId ) {
		this.m_ids[USERID] = p_userId;
	}
	
	public String getPassword() {
		return m_password;
	}
	public void setPassword(String p_password) {
		m_password = p_password;
	}
	public String getEmail() {
		return m_email;
	}
	public void setEmail(String p_email) {
		m_email = p_email;
	}
	public String getName() {
		return m_name;
	}
	public void setName(String p_name) {
		m_name = p_name;
	}
	public Date getDateRegistered() {
		return m_dateRegistered;
	}
	public void setDateRegistered(Date p_dateRegistered) {
		m_dateRegistered = p_dateRegistered;
	}
	
	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
