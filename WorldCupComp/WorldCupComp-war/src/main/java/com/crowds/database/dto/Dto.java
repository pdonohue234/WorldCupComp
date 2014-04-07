/**
 * AbstractDto - Data Transfer Object is the meta implementation for all Base Dto's.
 * 
 * A data transfer object contains the actual data which is specifed in the Dao. It represents
 * a row in a particular table (detailed in a Dao).
 * 
 * You can think of this as a row from one of the Daos (Table definitions)
 */
package com.crowds.database.dto;

public abstract class Dto<T> {
	
	protected Object[]	m_ids;
	private String		m_displayName;
	private boolean 	m_persistent;
	private String		m_lastModifiedDateTime;
	private boolean		m_recordPersisted;
	
	protected Dto() {
		this.m_displayName = getDtoName();
	}
	
	protected Dto( Object[] p_ids ) {
		this();
		this.m_ids = p_ids;
	}
	
	protected Dto( String p_id ) {
		this( new Object[] { p_id } );
	}

	
	public final Object[] getIds() {
		return m_ids;
	}

	public final void setIds(Object[] p_ids) {
		m_ids = p_ids;
	}
	
	public final String getIdsAsString() {
		return (String) m_ids[0];
	}

	public String getDisplayName() {
		return m_displayName;
	}

	public void setDisplayName(String p_displayName) {
		m_displayName = p_displayName;
	}

	public boolean isPersistent() {
		return m_persistent;
	}

	public void setPersistent(boolean p_persistent) {
		m_persistent = p_persistent;
	}

	public String getLastModifiedDateTime() {
		return m_lastModifiedDateTime;
	}

	public void setLastModifiedDateTime(String p_lastModifiedDateTime) {
		m_lastModifiedDateTime = p_lastModifiedDateTime;
	}

	public boolean isRecordPersisted() {
		return m_recordPersisted;
	}

	public void setRecordPersisted(boolean p_recordPersisted) {
		m_recordPersisted = p_recordPersisted;
	}
	
	public final String getDtoName() {
		return getClass().getSimpleName();
	}

}
