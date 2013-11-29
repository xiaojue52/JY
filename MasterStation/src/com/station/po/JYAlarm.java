package com.station.po;

import java.sql.Date;

public class JYAlarm {
	private String id;
	private Date alarmDatetime;
	private String alarmText;
	private String note;
	private String repairUser; //维修人员
	private int status;
	private JYCabinet cabinet;
	private JYDevice device;

	
	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}