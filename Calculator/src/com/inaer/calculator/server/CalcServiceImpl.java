package com.inaer.calculator.server;

import com.inaer.calculator.client.CalcService;
import com.inaer.calculator.persistence.Binary;
import com.inaer.calculator.persistence.CalcUtils;

import java.text.SimpleDateFormat;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class CalcServiceImpl extends RemoteServiceServlet implements CalcService {

	public String[] calcServer(String value) throws IllegalArgumentException {
		StringBuilder conver = new StringBuilder();
		String binary="0";
		try {
			binary = toBinary(value);
			insert(value, binary);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			List<Binary> listBinaries = entries();
			for(Binary b : listBinaries){
				conver.append("Value:" + b.getValue() + " Binary:" + b.getBinValue() + " Date:" + sdf.format(b.getDate()) + "<br>");
			}
		} catch (NumberFormatException nfe) {
			conver.append("Can't convert " + value);
		}
		return new String[]{binary, conver.toString()};
	}
	
	public static String toBinary(String value) {
		Integer n = Integer.valueOf(value);
		return Integer.toString(n.intValue(),2);
	}
	
	private void insert (final String value, final String binaryValue) {
		CalcUtils.insert(value, binaryValue);
	}
	
	private List<Binary> entries () {
		return CalcUtils.getEntries();
	}
}
