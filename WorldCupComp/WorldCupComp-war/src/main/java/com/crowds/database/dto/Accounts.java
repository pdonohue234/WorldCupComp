package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Accounts extends Dto<Accounts> implements Serializable {
	
	private static final long serialVersionUID = 5357816548361689586L;
	
	public static final String MAPPED_TABLE_NAME = "ACCOUNTS";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"ACCOUNTID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"MONEY_RECEIVED", "PRIZE_MONEY_OWED", "OTHER_EXPENSES", 
		"DATE", "TIME"}; 
	
	public static final int NUMBER_OF_KEYS = 1;
	public static final int ACCOUNTID = 0;
	
	private double	m_moneyReceived;
	private double	m_prizeMoneyOwed;
	private double	m_otherExpenses;
	private Date	m_date;
	private Integer	m_time; //Hhmmss
	
	
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
	
	
	public final double getMoneyReceived() {
		return m_moneyReceived;
	}


	public final void setMoneyReceived(double p_moneyReceived) {
		m_moneyReceived = p_moneyReceived;
	}


	public final double getPrizeMoneyOwed() {
		return m_prizeMoneyOwed;
	}


	public final void setPrizeMoneyOwed(double p_prizeMoneyOwed) {
		m_prizeMoneyOwed = p_prizeMoneyOwed;
	}


	public final double getOtherExpenses() {
		return m_otherExpenses;
	}


	public final void setOtherExpenses(double p_otherExpenses) {
		m_otherExpenses = p_otherExpenses;
	}


	public final Date getDate() {
		return m_date;
	}


	public final void setDate(Date p_date) {
		m_date = p_date;
	}


	public final Integer getTime() {
		return m_time;
	}


	public final void setTime(Integer p_time) {
		m_time = p_time;
	}


	public String getMappedTableName() {
		return MAPPED_TABLE_NAME;
	}
	
	public String[] getKeyFieldNames() {
		return KEY_FIELD_NAMES;
	}
}
