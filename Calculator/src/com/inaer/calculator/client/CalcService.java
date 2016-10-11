package com.inaer.calculator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("calc")
public interface CalcService extends RemoteService {
	String calcServer(String value) throws IllegalArgumentException;
}
