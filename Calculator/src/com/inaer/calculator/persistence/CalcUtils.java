package com.inaer.calculator.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.FetchOptions.Builder;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.inaer.calculator.persistence.Binary;

public class CalcUtils {
	
	private static final int FETCH_MAX_RESULTS = 10;
	
	public static void insert(final String value, final String binaryValue) {
		final DatastoreService datastoreService = DSF.getDatastoreService();
		final Binary binary = new Binary(value, binaryValue, new Date());		
		datastoreService.put(binary.getEntity());
	}
	
	public static List<Binary> getEntries() {
		final DatastoreService datastoreService = DSF.getDatastoreService();
		final Query query = configureQuery();
		final FetchOptions fetchOptions = configureFetchOptions();

		final List<Binary> binaries = new ArrayList<Binary>();
		for (Entity entity: datastoreService.prepare(query).asList(fetchOptions)) {
			binaries.add(convertEntityToBinary (entity));	
		}		
		return binaries;
	}
	
	private static Binary convertEntityToBinary (final Entity entity) {
		final String value = (String) entity.getProperty(Binary.VALUE);
		final String binValue = (String) entity.getProperty(Binary.BIN_VALUE);
		final Date fecha = (Date) entity.getProperty(Binary.DATE);
		return new Binary(value, binValue, fecha);
	}
	
	private static Query configureQuery () {
		final Query query = new Query(Binary.BINARY_ENTITY);
		query.addFilter(Binary.DATE, Query.FilterOperator.NOT_EQUAL, null);
		query.addSort(Binary.DATE, SortDirection.DESCENDING);		
		return query;
	}
	
	private static FetchOptions configureFetchOptions () {
		return Builder.withLimit(FETCH_MAX_RESULTS);	
	}
}
