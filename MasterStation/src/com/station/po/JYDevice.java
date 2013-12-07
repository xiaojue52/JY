package com.station.po;

import java.util.Date;
import java.util.List;

public class JYDevice {
	private String deviceId;
	private String deviceNumber;
	private JYCabinet cabinet;
	private String name;
	private String status;
	private String note;
	private Integer tag;
	private List<JYDetector> detectorList;
	private JYAlarm alarm;
	private Date createTime;
	private Integer positionNumber;
	
	public Integer getPositionNumber() {
		return positionNumber;
	}
	public void setPositionNumber(Integer positionNumber) {
		this.positionNumber = positionNumber;
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
	public List<JYDetector> getDetectorList() {
		return detectorList;
	}
	public void setDetectorList(List<JYDetector> detectorList) {
		this.detectorList = detectorList;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceNumber() {
		return deviceNumber;
	}
	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}
	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}

}