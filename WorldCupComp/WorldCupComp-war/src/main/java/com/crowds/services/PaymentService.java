package com.crowds.services;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

import com.crowds.database.dao.PaymentDao;
import com.crowds.database.dto.Payment;

public class PaymentService extends PaymentDao {
	
	public Logger			m_logger	= 	Logger.getLogger(PaymentService.class.getName());
	
	/** 
	 * Find list of all payments in system
	 * 
	 * @return list of payments
	 */
	public List<Payment> getPaymentList() {  
		try {
			return this.findAll();
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find ALL Payment records.");
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;		  
	}
	
	/**
	 * Find a single Payment based on the userId and paymentId
	 * @param userId
	 * @param paymentId
	 * @return
	 */
	public Payment getPayment(String transactionId, String p_userId) { 
		try {
			if( StringUtils.isNotBlank(p_userId) && StringUtils.isNotBlank(transactionId)) {
				return this.findById(transactionId, p_userId); 
			}
			else {
				this.m_logger.warning("Cannot find User's Payment record because of empty Keys passed!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User's Payment record for Keys: " + p_userId +" and "+ transactionId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	} 
	
	/**
	 * Find a list of users payments based on the userId 
	 * @param p_userId
	 * @return
	 */
	public List<Payment> getUsersPayments(String p_userId) { 
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				return this.findByUserId(p_userId); 
			}
			else {
				this.m_logger.warning("Cannot find User's Payments records based on empty userId key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to find User's Payments records with userId: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		} 
		return null;
	}	
	
	/** 
	 * Insert a single Payment record into the database
	 * @param p_payment
	 * @return
	 */
	public boolean insertData(Payment p_payment) {  
		try {
			if(p_payment != null && StringUtils.isNotEmpty(p_payment.getUserId()) && 
					StringUtils.isNotEmpty(p_payment.getTransactionId() )) {
				//Check if record exists for that userId already
				Payment paymentExists = this.findById(p_payment.getTransactionId(), p_payment.getUserId());
				if( paymentExists == null ) {
					int rowCount = this.insert(p_payment);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Payments records were not added successfully for Key:" + p_payment.getUserId());
				}
				else {
					this.m_logger.warning("User's Payments records were not added - User's Payments records already exists "
							+ "for Keys:" + p_payment.getUserId() + " and " + p_payment.getTransactionId());
				}
			}
			else {
				this.m_logger.warning("Cannot add User's Payments record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to create a User's Payments record for Key: " + p_payment.getUserId() 
					+ " and " + p_payment.getTransactionId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Update a single user's Payments record in the database
	 * @param p_payment
	 * @return
	 */
	public boolean updateData(Payment p_payment) {  
		try {
			if(p_payment != null && StringUtils.isNotEmpty(p_payment.getUserId()) && 
					StringUtils.isNotEmpty(p_payment.getTransactionId() )) {
				//Ensure record exists for that userId already
				Payment paymentExists = this.findById(p_payment.getTransactionId(), p_payment.getUserId());
				if( paymentExists != null ) {
					int rowCount = this.update(p_payment);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Payments record was not updated successfully for Key:"  + p_payment.getUserId() 
								+ " and " + p_payment.getTransactionId());
				}
				else 
					this.m_logger.warning("User's Payments record was not updated - User record does NOT exists for Key:"  + p_payment.getUserId() 
							+ " and " + p_payment.getTransactionId());
			}
			else {
				this.m_logger.warning("Cannot update User's Payments record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to update a User's Payments record for Key: "  + p_payment.getUserId() 
					+ " and " + p_payment.getTransactionId());
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	

	/** 
	 * Remove a user's Payments record from the database
	 * @param p_paymentId
	 * @return
	 */
	public boolean deleteUserData(String p_userId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId)) {
				//Check if record exists before attempting to delete it
				List<Payment> paymentExists = this.findByUserId(p_userId);
				if( paymentExists != null && paymentExists.size() > 0) {
					for( Payment payment : paymentExists ) {
						int rowCount = this.delete(payment);
						if(rowCount != -1)
							return true;	
						else
							this.m_logger.warning("User's Payments record was not deleted for Key:" + payment.getUserId() 
									+ " and " + payment.getTransactionId());
					}
				}
			}
			else {
				this.m_logger.warning("Cannot delete User's Payments record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete User's Paymentsrecord for Key: " + p_userId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	}  
	
	/** 
	 * Remove a user's Payments record from the database
	 * @param p_paymentId
	 * @return
	 */
	public boolean deleteData(String transactionId, String p_userId) {  
		try {
			if(StringUtils.isNotEmpty(p_userId) && StringUtils.isNotEmpty(transactionId )) {
				//Check if record exists before attempting to delete it
				Payment paymentExists = this.findById(transactionId, p_userId);
				if( paymentExists != null ) {
					int rowCount = this.delete(paymentExists);
					if(rowCount != -1)
						return true;	
					else
						this.m_logger.warning("User's Payments record was not deleted for Key:" + p_userId +" and "+ transactionId);
			
				}
			}
			else {
				this.m_logger.warning("Cannot delete User's Payments record for empty Key!");
			}
		}
		catch(Exception e) {
			this.m_logger.severe("Error attemping to delete User's Payments record for Key:" + p_userId +" and "+ transactionId);
			this.m_logger.severe(e.getLocalizedMessage());
		}
		return false;
	} 	
}
