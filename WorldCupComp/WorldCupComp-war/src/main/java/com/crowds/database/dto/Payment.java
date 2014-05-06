package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Payment extends Dto<Payment> implements Serializable {
	
	private static final long serialVersionUID = -12345678901234567L;
	
	public static final String MAPPED_TABLE_NAME = "Payments";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"Trans_ID", "User_ID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"Payment_Amount", "Payment_Method", "Payment_Received", "Time_Stamp"}; 
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int TRANSACTIONID = 0;
	public static final int USERID = 1;	

	private double 	m_paymentAmount;
	private String	m_paymentMethod;
	private String 	m_paymentReceived;
	private Date	m_date;
	
	
	/**
	 * Default Constructor
	 */
	public Payment() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	/**
	 * Overloaded Constructor - Accepts TransactionId 
	 */
	public Payment(String p_transactionId ) {
		super( new Object[] { p_transactionId, "" } );
	}
	
	
	/**
	 * Overloaded Constructor - Accepts UserId & paymentId
	 */
	public Payment(String p_transactionId, String p_userId) {
		super( new Object[] { p_transactionId, p_userId } );
	}

	public String getTransactionId() {
		return (String) m_ids[TRANSACTIONID];
	}
	
	public void setTransactionId( String p_transactionId ) {
		this.m_ids[TRANSACTIONID] = p_transactionId;
	}
	
	public String getUserId() {
		return (String) m_ids[USERID];
	}
	
	public void setUserId( String p_userId ) {
		this.m_ids[USERID] = p_userId;
	}
	
	public double getPaymentAmount() {
		return m_paymentAmount;
	}

	public void setPaymentAmount(double p_paymentAmount) {
		m_paymentAmount = p_paymentAmount;
	}
	
	public String getPaymentMethod() {
		return m_paymentMethod;
	}

	public void setPaymentMethod(String p_paymentMethod) {
		m_paymentMethod = p_paymentMethod;
	}

	public String getPaymentReceived() {
		return m_paymentReceived;
	}

	public void setPaymentReceived(String p_paymentReceived) {
		m_paymentReceived = p_paymentReceived;
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
