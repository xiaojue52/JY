package com.station.po;

import java.sql.Date;
import java.sql.Time;

public class DeviceHistory {
	private Integer id;
	private String name;
	private String deviceBox;
	private String note;
	private double warningTemperature;
	private double currentData;
	private double enviTemp;
	private String status;
	private String owner;
	private String reason;
	private String identify;
	private Date createDate;
	private Time createTime;
	private String subBox;
	
	public String getSubBox() {
		return subBox;
	}
	public void setSubBox(String subBox) {
		this.subBox = subBox;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public double getEnviTemp() {
		return enviTemp;
	}
	public void setEnviTemp(double enviTemp) {
		this.enviTemp = enviTemp;
	}
	public String getDeviceBox() {
		return deviceBox;
	}
	public void setDeviceBox(String deviceBox) {
		this.deviceBox = deviceBox;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public double getWarningTemperature() {
		return warningTemperature;
	}
	public void setWarningTemperature(double warningTemperature) {
		this.warningTemperature = warningTemperature;
	}
	public double getCurrentData() {
		return currentData;
	}
	public void setCurrentData(double currentData) {
		this.currentData = currentData;
	}

}
