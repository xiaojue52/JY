package com.station.po;

import java.sql.Date;
import java.sql.Time;

public class JYAlarm {
	private String id;
	private Date createDate;
	private Time createTime;
	private String alarmText;
	private String note;
	private String repairUser; //维修人员
	private String status;
	private JYDevice device;
	private String isCabinet;
	private java.util.Date date;
	
	
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public Date getCreateDate() {
		createDate = new java.sql.Date(date.getTime());
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Time getCreateTime() {
		createTime = new java.sql.Time(date.getTime());
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
	public JYDevice getDevice() {
		return device;
	}
	public void setDevice(JYDevice device) {
		this.device = device;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAlarmText() {
		return alarmText;
	}
	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getIsCabinet() {
		return isCabinet;
	}
	public void setIsCabinet(String isCabinet) {
		this.isCabinet = isCabinet;
	}

	
}