package com.station.monitor.action;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.constant.LoginStatus;
import com.station.po.JYAlarm;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class UnhandledAlarmAction extends ActionSupport {
	
	private JYAlarmService alarmService;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUserGroup;
	private String queryStartDate;
	private String queryEndDate;
	private String queryDevice;
	private String queryRepairStatus;
	private int unhandledTag = 0;//全部
	private JYAlarm alarmTemp;
	

	public String getQueryUserGroup() {
		return queryUserGroup;
	}

	public void setQueryUserGroup(String queryUserGroup) {
		this.queryUserGroup = queryUserGroup;
	}

	public JYAlarm getAlarmTemp() {
		return alarmTemp;
	}

	public void setAlarmTemp(JYAlarm alarmTemp) {
		this.alarmTemp = alarmTemp;
	}

	public int getUnhandledTag() {
		return unhandledTag;
	}

	public void setUnhandledTag(int unhandledTag) {
		this.unhandledTag = unhandledTag;
	}

	public String getQueryLine() {
		return queryLine;
	}

	public void setQueryLine(String queryLine) {
		this.queryLine = queryLine;
	}

	public String getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(String queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}


	public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public String getQueryDevice() {
		return queryDevice;
	}

	public void setQueryDevice(String queryDevice) {
		this.queryDevice = queryDevice;
	}

	public String getQueryRepairStatus() {
		return queryRepairStatus;
	}

	public void setQueryRepairStatus(String queryRepairStatus) {
		this.queryRepairStatus = queryRepairStatus;
	}

	public void setAlarmService(JYAlarmService alarmService) {
		this.alarmService = alarmService;
	}
	public String createSql(){
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(cabinet.userGroup.groupName = '"+Constant.getSessionStringAttr("userGroup")+"' or cabinet.userGroup.groupName = '--') and ";
		}
		String hql = "from JYAlarm alarm where "+temp;
		if (unhandledTag==1){
			queryLine = null;queryNumber=null;queryType=null;queryUserGroup=null;queryStartDate=null;queryEndDate=null;
			queryRepairStatus = "0";
		}
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUserGroup == null || queryUserGroup.length() == 0)
			queryUserGroup = "%";
		if (queryStartDate == null || queryStartDate.length() == 0)
			queryStartDate = "1000-01-01";
		if (queryEndDate == null || queryEndDate.length() == 0)
			queryEndDate = "9999-12-12";
		if (queryDevice == null || queryDevice.length() == 0)
			queryDevice = "%";
		if (queryRepairStatus == null || queryRepairStatus.length() == 0)
			queryRepairStatus = "%";
		hql = hql + "(alarm.device.cabinet.line.name like :queryLine and "
				+ "alarm.device.cabinet.cabNumber like :queryNumber and "
				+ "alarm.device.name like :queryDevice and alarm.status like :queryRepairStatus and "
				+ "alarm.device.cabinet.cabType.value like :queryType and alarm.date>= TO_DATE('"+queryStartDate+"','YYYY-MM-DD') and "
				+ "alarm.date <= TO_DATE('"+queryEndDate+"','YYYY-MM-DD') and "
				+ "alarm.device.cabinet.userGroup.groupName like :queryUserGroup )" +
				" ORDER BY alarm.date DESC";
		unhandledTag = 0;
		return hql;
	}
	public void getUnhanledCountAction(){
		unhandledTag = 1;
		final String hql = this.createSql();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("queryLine", "%"+queryLine+"%");
		parameters.put("queryNumber", "%"+queryNumber+"%");
		parameters.put("queryDevice", "%"+queryDevice+"%");
		parameters.put("queryRepairStatus", "%"+queryRepairStatus+"%");
		parameters.put("queryType", "%"+queryType+"%");
		parameters.put("queryUserGroup", "%"+queryUserGroup+"%");
		int count = this.alarmService.getTotalCount(hql,parameters);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("unhandledCount", count);
        Constant.flush(dataMap);
	}
}
