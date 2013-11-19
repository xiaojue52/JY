package com.station.po;

public class JYDetector {
	private String detectorId;
	private String name;
	private JYDevice device;
	private String unit;
	private Integer tag;
	public String getDetectorId() {
		return detectorId;
	}
	public void setDetectorId(String detectorId) {
		this.detectorId = detectorId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JYDevice getDevice() {
		return device;
	}
	public void setDevice(JYDevice device) {
		this.device = device;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}

}