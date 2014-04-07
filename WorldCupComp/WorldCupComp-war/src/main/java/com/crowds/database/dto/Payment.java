package com.crowds.database.dto;

import java.io.Serializable;
import java.util.Date;

public class Payment extends Dto<Payment> implements Serializable {
	
	private static final long serialVersionUID = -12345678901234567L;
	
	public static final String MAPPED_TABLE_NAME = "Payment";
	
	protected static final String[] KEY_FIELD_NAMES = new String[] {"USERID", "PAYMENTID"}; 
	
	protected static final String[] FIELD_NAMES = new String[] {"PAYMENT_METHOD", "PAYMENT_RECEIVED", "DATE", "TIME"}; 
	
	public static final int NUMBER_OF_KEYS = 2;
	public static final int USERID = 0;
	public static final int PAYMENTID = 1;	
	
	private String	m_paymentMethod;
	private double 	m_paymentReceived;
	private Date	m_date;
	private Integer	m_time; //Hhmmss
	
	
	/**
	 * Default Constructor
	 */
	public Payment() {
		super( new Object[NUMBER_OF_KEYS]);
	}
	
	/**
	 * Overloaded Constructor - Accepts UserId 
	 */
	public Payment(String p_userId ) {
		super( new Object[] { p_userId, "" } );
	}
	
	
	/**
	 * Overloaded Constructor - Accepts UserId & paymentId
	 */
	public Payment(String p_userId, String p_paymentId) {
		super( new Object[] { p_userId, p_paymentId } );
	}
	
	
	public String getUserId() {
		return (String) m_ids[USERID];
	}
	
	public void setUserId( String p_userId ) {
		this.m_ids[USERID] = p_userId;
	}
	

	public String getPaymentId() {
		return (String) m_ids[PAYMENTID];
	}
	
	public void setPaymentId( String p_paymentId ) {
		this.m_ids[PAYMENTID] = p_paymentId;
	}
	
	public final String getPaymentMethod() {
		return m_paymentMethod;
	}

	public final void setPaymentMethod(String p_paymentMethod) {
		m_paymentMethod = p_paymentMethod;
	}

	public final double getPaymentReceived() {
		return m_paymentReceived;
	}

	public final void setPaymentReceived(double p_paymentReceived) {
		m_paymentReceived = p_paymentReceived;
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
