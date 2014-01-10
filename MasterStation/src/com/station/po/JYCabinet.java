package com.station.po;

import java.util.Date;
import java.util.List;


public class JYCabinet {
	private String cabId;
	private JYLine line;
	private String cabNumber;
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
	private Date createTime; //创建时间
	private Integer status; //-1未启用，0停用，1启用
	private Date detectTime; //监测时间
	private JYUserGroup userGroup;

	public JYUserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(JYUserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public Date getDetectTime() {
		return detectTime;
	}
	public void setDetectTime(Date detectTime) {
		this.detectTime = detectTime;
	}

	/**
	 * -1未启用，0停用，1启用
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
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