package com.station.po;

import java.sql.Time;
import java.sql.Date;

public class JYHistoryMonthChartData {
	private Integer id;
	private Date createDate;
	private Time createTime;
	private JYDetector detector;
	private JYCabinetHistory cabinetHistory;
	private Float value;
	private java.util.Date date;
	private Integer tag;


	public Integer getTag() {
		return tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public JYCabinetHistory getCabinetHistory() {
		return cabinetHistory;
	}

	public void setCabinetHistory(JYCabinetHistory cabinetHistory) {
		this.cabinetHistory = cabinetHistory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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