package com.station.po;


public class JYHistoryChartData {
	private Integer id;
	private JYDetector detector;
	private Float value;
	private java.util.Date date;


	public java.util.Date getDate() {
		return date;
	}

	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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