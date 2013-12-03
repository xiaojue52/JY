package com.station.po;

import java.sql.Date;
import java.sql.Time;
import java.util.List;


public class JYCabinet {
	private String cabId;
	private JYLine line;
	private String cabNumber;
	private JYUser user;
	//private String powerLevel;
	private JYConstant powerLevel;
	private JYConstant cabType;
	private String simNumber;
	private String simSNumber;
	private Integer tag;
	private JYAlarmTypeCollect alarmTypeCollect;
	private List<JYDevice> deviceList;
	private JYAlarm alarm;
	private String note;
	private Date createDate;
	private Time createTime;
	
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Time getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
	public JYAlarm getAlarm() {
		return alarm;
	}
	public void setAlarm(JYAlarm alarm) {
		this.alarm = alarm;
	}
	public List<JYDevice> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<JYDevice> deviceList) {
		this.deviceList = deviceList;
	}
	public JYAlarmTypeCollect getAlarmTypeCollect() {
		return alarmTypeCollect;
	}
	public void setAlarmTypeCollect(JYAlarmTypeCollect alarmTypeCollect) {
		this.alarmTypeCollect = alarmTypeCollect;
	}
	public String getCabId() {
		return cabId;
	}
	public void setCabId(String cabId) {
		this.cabId = cabId;
	}

	public JYLine getLine() {
		return line;
	}
	public void setLine(JYLine line) {
		this.line = line;
	}
	public JYUser getUser() {
		return user;
	}
	public void setUser(JYUser user) {
		this.user = user;
	}
	public String getCabNumber() {
		return cabNumber;
	}
	public void setCabNumber(String cabNumber) {
		this.cabNumber = cabNumber;
	}

	public JYConstant getPowerLevel() {
		return powerLevel;
	}
	public void setPowerLevel(JYConstant powerLevel) {
		this.powerLevel = powerLevel;
	}
	public JYConstant getCabType() {
		return cabType;
	}
	public void setCabType(JYConstant cabType) {
		this.cabType = cabType;
	}
	public String getSimNumber() {
		return simNumber;
	}
	public void setSimNumber(String simNumber) {
		this.simNumber = simNumber;
	}
	public String getSimSNumber() {
		return simSNumber;
	}
	public void setSimSNumber(String simSNumber) {
		this.simSNumber = simSNumber;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	
}