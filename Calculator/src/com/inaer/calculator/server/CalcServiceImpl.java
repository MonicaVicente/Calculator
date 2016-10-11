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

	public String calcServer(String value) throws IllegalArgumentException {
		String binary = toBinary(value);
		insert(value, binary);
		
		StringBuilder conver = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		List<Binary> listBinaries = entries();
		for(Binary b : listBinaries){
			conver.append("Value:" + b.getValue() + " Binary:" + b.getBinValue() + " Date:" + sdf.format(b.getDate()) + "<br>");
		}
		return conver.toString();
	}
	
	public static String toBinary(String value) {
		int n = Integer.valueOf(value);
		String bin = "";
		if (n > 0) {
			while (n > 0) {
				if (n % 2 == 0) {
					bin = "0" + bin;
				} else {
					bin = "1" + bin;
				}
				n = (int) n / 2;
			}
		} else if (n == 0) {
			bin = "0";
		} else {
			bin = null;
		}
		return bin;
	}
	
	private void insert (final String value, final String binaryValue) {
		CalcUtils.insert(value, binaryValue);
	}
	
	private List<Binary> entries () {
		return CalcUtils.getEntries();
	}
}
