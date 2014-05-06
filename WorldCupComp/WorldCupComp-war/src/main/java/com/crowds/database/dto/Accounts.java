package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Accounts extends Dto<Accounts> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "Accounts";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Account_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Money_Received", "Prize_Money_Owed", "Other_Expenses", "TimeStamp", 
		"DATE", "TIME"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int ACCOUNTID = 0;
	
	private double	m_moneyReceived;
	private double	m_prizeMoneyOwed;
	private double	m_otherExpenses;
	private Date	m_date;
	
	
	/**
	 * Default Constructor
	 */
	public Accounts() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	
	/**
	 * Overloaded Constructor - Accepts AccountId
	 */
	public Accounts(String p_accountId) {
		super( p_accountId );
	}
	
	
	public String getAccountId() {
		return (String) m_ids[ACCOUNTID];
	}
	
	public void setAccountId( String p_accountId ) {
		this.m_ids[ACCOUNTID] = p_accountId;
	}
	
	
	public double getMoneyReceived() {
		return m_moneyReceived;
	}


	public void setMoneyReceived(double p_moneyReceived) {
		m_moneyReceived = p_moneyReceived;
	}


	public double getPrizeMoneyOwed() {
		return m_prizeMoneyOwed;
	}


	public void setPrizeMoneyOwed(double p_prizeMoneyOwed) {
		m_prizeMoneyOwed = p_prizeMoneyOwed;
	}


	public double getOtherExpenses() {
		return m_otherExpenses;
	}


	public void setOtherExpenses(double p_otherExpenses) {
		m_otherExpenses = p_otherExpenses;
	}


	public Date getDate() {
		return m_date;
	}


	public void setDate(Date p_date) {
		m_date = p_date;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
