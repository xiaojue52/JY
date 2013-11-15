package com.station.po;

import java.sql.Date;

public class JYHistory {
	private Date collectDatetime;
	private JYDetector detector;
	private Float value;
	public Date getCollectDatetime() {
		return collectDatetime;
	}
	public void setCollectDatetime(Date collectDatetime) {
		this.collectDatetime = collectDatetime;
	}
	public JYDetector getDetector() {
		return detector;
	}
	public void setDetector(JYDetector detector) {
		this.detector = detector;
	}
	public Float getValue() {
		return value;
	}
	public void setValue(Float value) {
		this.value = value;
	}
	
}