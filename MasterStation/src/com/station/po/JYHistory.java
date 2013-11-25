package com.station.po;

import java.sql.Time;
import java.sql.Date;

public class JYHistory {
	private Integer id;
	private Date collectDate;
	private Time CollectTime;
	private JYDetector detector;
	private Float value;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	public Time getCollectTime() {
		return CollectTime;
	}

	public void setCollectTime(Time collectTime) {
		CollectTime = collectTime;
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