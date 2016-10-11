package com.inaer.calculator.persistence;

import java.util.Date;

import com.google.appengine.api.datastore.Entity;

public class Binary {
	
	public static final String BINARY_ENTITY = "Binary";
	
	public static final String VALUE = "value";
	
	public static final String BIN_VALUE = "binValue";
	
	public static final String DATE = "date";
	
	private Entity entity = new Entity (BINARY_ENTITY);
	
	public Binary(final String value, final String binary, final Date fecha) {
		entity.setProperty(VALUE, value);
		entity.setProperty(BIN_VALUE, binary);
		entity.setProperty(DATE, fecha);
	}

	public String getValue() {
		return (String) entity.getProperty(VALUE);
	}

	public String getBinValue() {
		return (String) entity.getProperty(BIN_VALUE);
	}
	
	public Date getDate() {
		return (Date) entity.getProperty(DATE);
	}
	
	public Entity getEntity () {
		return entity;
	}
}
