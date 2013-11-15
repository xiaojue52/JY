package com.station.po;

import java.sql.Date;

public class JYAlarm {
	private String alarmId;
	private Date alarmDatetime;
	private String alarmText;
	private String alarmType;
	private String user;
	private String status;
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public Date getAlarmDatetime() {
		return alarmDatetime;
	}
	public void setAlarmDatetime(Date alarmDatetime) {
		this.alarmDatetime = alarmDatetime;
	}
	public String getAlarmText() {
		return alarmText;
	}
	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}