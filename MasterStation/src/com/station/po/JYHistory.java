package com.station.po;

import java.sql.Time;
import java.sql.Date;

public class JYHistory {
	private Integer id;
	private Date createDate;
	private Time createTime;
	private JYDetector detector;
	private JYCabinetHistory cabinetHistory;
	private Float value;


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