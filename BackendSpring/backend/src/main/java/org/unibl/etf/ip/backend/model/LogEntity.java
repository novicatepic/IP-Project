package org.unibl.etf.ip.backend.model;

public class LogEntity {

    private String logData;

    public LogEntity() {}

    public LogEntity(String data) {
        logData = data;
    }

    public String getLogData() {
        return logData;
    }

    public void setLogData(String logData) {
        this.logData = logData;
    }
}
