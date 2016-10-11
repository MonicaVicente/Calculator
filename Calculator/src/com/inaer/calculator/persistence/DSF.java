package com.inaer.calculator.persistence;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DSF {
	
	private static final DatastoreService INSTANCE = DatastoreServiceFactory.getDatastoreService();

	public static DatastoreService getDatastoreService() {
		return INSTANCE;
	}

	private DSF() {
	}
}
