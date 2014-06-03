package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class User extends Dto<User> implements Serializable {
	
	private static final long serialVersionUID = -12345678901234567L;
	
	public static final String MAPPED_TABLE_NAME = "Users";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"User_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Password", "Trans_ID", "Name", "Date_Registered", "Private_Comp_Name"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int USERID = 0;
	
	private String	password;
	private String 	transId;
	private String	name;
	private Date	dateRegistered;
	private String	privateCompName;
	private boolean newPrivateCompName;
	
	
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
		return password;
	}
	public void setPassword(String p_password) {
		password = p_password;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String p_transId) {
		transId = p_transId;
	}
	public String getName() {
		return name;
	}
	public void setName(String p_name) {
		name = p_name;
	}
	public Date getDateRegistered() {
		return dateRegistered;
	}
	public void setDateRegistered(Date p_dateRegistered) {
		dateRegistered = p_dateRegistered;
	}
	public String getPrivateCompName() {
		return privateCompName;
	}
	public void setPrivateCompName(String p_privateCompName) {
		privateCompName = p_privateCompName;
	}	
	public boolean getNewPrivateCompName() {
		return newPrivateCompName;
	}
	public void setNewPrivateCompName(boolean p_newPrivateCompName) {
		newPrivateCompName = p_newPrivateCompName;
	}	
	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
