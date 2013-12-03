package com.station.po;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;


public class JYCabinetHistory {
	private Integer id;
	private JYCabinet cabinet;
	private Date createDate;
	private Time createTime;
	private List<JYHistory> historyList;
	private Map<JYDevice, List<JYHistory>> map;
	
	public Time getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}