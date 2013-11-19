package com.station.po;

public class JYCabinet {
	private String cabId;
	private JYLine line;
	private String cabNumber;
	private String cabType;
	private JYUser user;
	private String powerLevel;
	private String simNumber;
	private String simSNumber;
	private Integer tag;
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
	public String getCabType() {
		return cabType;
	}
	public void setCabType(String cabType) {
		this.cabType = cabType;
	}

	public String getPowerLevel() {
		return powerLevel;
	}
	public void setPowerLevel(String powerLevel) {
		this.powerLevel = powerLevel;
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