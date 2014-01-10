package com.station.datahandler.alarm.action;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.constant.LoginStatus;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class UnhandledAlarmAction extends ActionSupport {
	
	private JYAlarmService alarmService;
	private String queryRepairStatus;

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
		if (queryRepairStatus == null || queryRepairStatus.length() == 0)
			queryRepairStatus = "%";
		hql = hql + "(alarm.status like :queryRepairStatus )" +
				" ORDER BY alarm.date DESC";
		return hql;
	}
	public void getUnhanledCountAction(){		
		/*Date date = new Date();
		String str = String.valueOf(date.getTime());
		System.out.print(str.substring(str.length()-5)+"\n");*/
		queryRepairStatus = "0";
		final String hql = this.createSql();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("queryRepairStatus", "%"+queryRepairStatus+"%");
		int count = this.alarmService.getTotalCount(hql,parameters);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("unhandledCount", count);
        Constant.flush(dataMap);
	}
}
