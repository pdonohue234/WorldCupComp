package com.crowds.database.sql;

public class Sql {
	
	private String		m_sql;
	private Object[]	m_parameters;
	
	public Sql() {
		super();
	}
	
	public Sql(String p_sql, Object[] p_parameters) {
		super();
		m_sql = p_sql;
		m_parameters = p_parameters;
	}

	public final String getSql() {
		return m_sql;
	}

	public final void setSql(String p_sql) {
		m_sql = p_sql;
	}

	public final Object[] getParameters() {
		return m_parameters;
	}

	public final void setParameters(Object[] p_parameters) {
		m_parameters = p_parameters;
	}
}
