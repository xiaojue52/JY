package com.station.po;

import java.util.List;
import java.util.Map;


public class JYCabinetHistory {
	private String id;
	private JYCabinet cabinet;
	private List<JYHistory> historyList;
	private Map<JYDevice, List<JYHistory>> map;
	private java.util.Date date;
	
	
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public Map<JYDevice, List<JYHistory>> getMap() {
		return map;
	}
	public void setMap(Map<JYDevice, List<JYHistory>> map) {
		this.map = map;
	}
	public List<JYHistory> getHistoryList() {
		return historyList;
	}
	public void setHistoryList(List<JYHistory> historyList) {
		this.historyList = historyList;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
	}	
}