package org.unibl.etf.ip.model;

import java.io.Serializable;

public class LoggerData implements Serializable {

	private String logData;
	
	public LoggerData() {
		// TODO Auto-generated constructor stub
	}

	public LoggerData(String logData) {
		super();
		this.logData = logData;
	}

	public String getLogData() {
		return logData;
	}

	public void setLogData(String logData) {
		this.logData = logData;
	}
	
	

}
