package com.station.po;


public class JYHistory {
	private Integer id;
	private JYDetector detector;
	private JYCabinetHistory cabinetHistory;
	private Float value;
	private java.util.Date date;


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