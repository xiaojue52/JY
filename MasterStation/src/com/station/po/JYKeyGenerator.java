package com.station.po;

public class JYKeyGenerator {
	private Integer id;
	private Integer userId;
	private Integer lineId;
	private Integer deviceId;
	private Integer detectorId;
	private Integer cabId;
	
	public Integer getCabId() {
		return cabId;
	}

	public void setCabId(Integer cabId) {
		this.cabId = cabId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getDetectorId() {
		return detectorId;
	}

	public void setDetectorId(Integer detectorId) {
		this.detectorId = detectorId;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}