package com.station.po;

import java.sql.Date;

public class JYAlarm {
	private String id;
	private Date alarmDatetime;
	private String alarmText;
	private String alarmType;
	private String repairUser; //维修人员
	private String status;
	private int isCabinet;
	private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public int getIsCabinet() {
		return isCabinet;
	}
	public void setIsCabinet(int isCabinet) {
		this.isCabinet = isCabinet;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public String getRepairUser() {
		return repairUser;
	}
	public void setRepairUser(String repairUser) {
		this.repairUser = repairUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}