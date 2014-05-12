package com.crowds.database.dao;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import com.crowds.database.dao.jdbc.AbstractDaoJdbc;
import com.crowds.database.dto.Payment;
import com.crowds.database.sql.Sql;

public class PaymentDao extends AbstractDaoJdbc<Payment>{

	public Logger			m_logger	= 	Logger.getLogger(PaymentDao.class.getName());
	
	protected static final String	SQL_TABLE_NAME 		= "Payments";
	protected static final String	SQL_TABLE_COLUMNS 	= "Trans_ID, User_ID, Payment_Amount, Payment_Method, Payment_Received, TimeStamp";
	
	protected static final String	SQL_SELECT_ALL 		= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME;
	protected static final String	SQL_SELECT_USINGKEYS= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Trans_ID=? AND User_ID=?";
	protected static final String	SQL_SELECT_USINGKEY1= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE Trans_ID=? ";
	protected static final String	SQL_SELECT_USINGKEY2= "SELECT " + SQL_TABLE_COLUMNS + " FROM " + SQL_TABLE_NAME + " WHERE User_ID=?";
	
	protected static final String	SQL_ADD				= "INSERT INTO " + SQL_TABLE_NAME + " (" + SQL_TABLE_COLUMNS + ") VALUES (?,?,?,?,?,?)";
	protected static final String	SQL_UPDATE			= "UPDATE " + SQL_TABLE_NAME + " SET Payment_Amount=?, Payment_Method=?, Payment_Received=?, TimeStamp=? WHERE Trans_ID=? AND User_ID=?";
	protected static final String	SQL_DELETE			= "DELETE FROM " + SQL_TABLE_NAME + " WHERE Trans_ID=? AND User_ID=?";
	
	
	public PaymentDao() {
		super();
	}
	
	
	/**
	 * Find all of the Payments in the Payment database table
	 * 
	 * @return list of Users
	 */
	protected List<Payment> findAll() {
		try {
			Sql l_sql = new Sql(SQL_SELECT_ALL, new Object[] {});
			List<Payment> payments = this.findAll(l_sql, new PaymentRowMapper());
			return payments;
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
			return null;
		}
	}
	
	/**
	 * Find a single Payment record for given unique user ID & payment ID
	 * @param p_transactionId
	 * @param p_userId
	 * @return Payments record
	 */
	protected Payment findById(String transactionId, String p_userId) {
		try {
			if( StringUtils.isNotBlank(p_userId) && StringUtils.isNotBlank(transactionId)) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEYS, new Object[] {transactionId, p_userId});
				Payment payment = this.findById(l_sql, new PaymentRowMapper());
				return payment;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Find a list of Payments record for given unique user ID 
	 * @param p_userId
	 * @return List of Payment records
	 */
	protected List<Payment> findByUserId(String p_userId) {
		try {
			if( StringUtils.isNotBlank(p_userId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY2, new Object[] {p_userId});
				List<Payment> payments = this.findAll(l_sql, new PaymentRowMapper());
				return payments;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}	
	
	/**
	 * Find a list of Payments record for given unique payment ID 
	 * @param p_paymentId
	 * @return List of Payment records
	 */
	protected List<Payment> findByTransactionId(String p_transactionId) {
		try {
			if( StringUtils.isNotBlank(p_transactionId) ) {
				Sql l_sql = new Sql(SQL_SELECT_USINGKEY1, new Object[] {p_transactionId});
				List<Payment> payments = this.findAll(l_sql, new PaymentRowMapper());
				return payments;
			}
		}
		catch(Exception e) {
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return null;
	}
	
	/**
	 * Insert a new Payment record into the database
	 * @param p_payment
	 * @return number of rows affected (-1 if error)
	 */
	protected int insert( Payment p_payment ) {
		try {
			if( StringUtils.isNotBlank(p_payment.getUserId()) && StringUtils.isNotBlank(p_payment.getTransactionId())) {
				validateFields( p_payment );
				Sql l_sql = new Sql(SQL_ADD, 
						new Object[] {p_payment.getTransactionId(), p_payment.getUserId(), p_payment.getPaymentAmount(), 
						p_payment.getPaymentMethod(), p_payment.getPaymentReceived(), this.getSdf().format(new Date())});
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
	 * Update a Payment record in the database
	 * @param p_payment
	 * @return number of rows affected (-1 if error)
	 */
	protected int update( Payment p_payment ) {
		try {
			if( StringUtils.isNotBlank(p_payment.getUserId()) && StringUtils.isNotBlank(p_payment.getTransactionId())) {
				validateFields(p_payment );
				Sql l_sql = new Sql(SQL_UPDATE, 
						new Object[] {p_payment.getPaymentAmount(), p_payment.getPaymentMethod(), p_payment.getPaymentReceived(), 
						this.getSdf().format(new Date()), p_payment.getTransactionId(), p_payment.getUserId()});
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
	 * Remove a Payment record from the database
	 * @param p_payment
	 * @return number of rows affected (-1 if error)
	 */
	protected int delete( Payment p_payment ) {
		try {
			if( StringUtils.isNotBlank(p_payment.getUserId()) && StringUtils.isNotBlank(p_payment.getTransactionId())) {
				Sql l_sql = new Sql(SQL_DELETE, 
						new Object[] {p_payment.getTransactionId(), p_payment.getUserId()});
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
	private void validateFields( Payment p_payment ) {
		p_payment.setPaymentMethod(( p_payment.getPaymentMethod() == null ? "" : p_payment.getPaymentMethod() ));
		p_payment.setPaymentReceived(( p_payment.getPaymentReceived() == null ? "N" : p_payment.getPaymentReceived() ));
	}
	
	@Override
	public PaymentRowMapper newRowMapper() {
		return new PaymentRowMapper();
	}
	
	/** 
	 * Map the ResultSet to Payment Dto
	 *
	 */
	protected class PaymentRowMapper implements ParameterizedRowMapper<Payment> {
		
		public PaymentRowMapper() {
			super();
		}
		
		public Payment mapRow(ResultSet rs, int index) {
			int seqn = 1;
			
			Payment dto = new Payment();
			try {
				dto.setTransactionId(rs.getString(seqn++));
				dto.setUserId(rs.getString(seqn++));
				dto.setPaymentAmount(rs.getDouble(seqn++));
				dto.setPaymentMethod(rs.getString(seqn++));
				dto.setPaymentReceived(rs.getString(seqn++));
				dto.setDate(getDate(rs.getDate(seqn++)));
			}
			catch(Exception e) {
				m_logger.severe(e.getLocalizedMessage());
			}
			
			return dto;
		}
	}
}
