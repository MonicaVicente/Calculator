package com.inaer.calculator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface CalcServiceAsync {
	void calcServer(String value, AsyncCallback<String> callback) throws IllegalArgumentException;
}
