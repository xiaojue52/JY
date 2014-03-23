package com.station.po;

public class JYAlarm {
	public final static int DEVICEREEOR = 2;
	public final static int DEVICEOFFLINE = 1;
	public final static int HEARTBEATOFFLINE = 0;
	public final static int TERMINALREPEAT = 3;
	public final static int TEMPCHANGTOFAST = 4;
	public final static int TEMPEXCEPTION = 5;
	public final static int TEMPERROR = 6;
	private String id;
	private String alarmText;
	private String note;
	private String repairUser; //维修人员
	private String status; //维修状态
	private JYDevice device;
	private String isCabinet; //是否是柜子
	private java.util.Date date;
	private String type; //用来区分报警等级，针对柜子报警
	private Integer times;//重复次数
	private String condition;
	
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getTimes() {
		return times;
	}
	public void setTimes(Integer times) {
		this.times = times;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	public JYDevice getDevice() {
		return device;
	}
	public void setDevice(JYDevice device) {
		this.device = device;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getAlarmText() {
		return alarmText;
	}
	public void setAlarmText(String alarmText) {
		this.alarmText = alarmText;
	}

	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRepairUser() {
		return repairUser;
	}
	public void setRepairUser(String repairUser) {
		this.repairUser = repairUser;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getIsCabinet() {
		return isCabinet;
	}
	public void setIsCabinet(String isCabinet) {
		this.isCabinet = isCabinet;
	}

	
}