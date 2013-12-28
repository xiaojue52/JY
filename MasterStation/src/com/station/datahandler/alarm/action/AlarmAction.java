package com.station.datahandler.alarm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.constant.LoginStatus;
import com.station.data.DataList;
import com.station.pagebean.PageBean;
import com.station.po.JYAlarm;
import com.station.po.JYConstant;
import com.station.po.JYUser;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class AlarmAction extends ActionSupport {
	
	private JYAlarmService alarmService;
	private DataList dataList;
	private PageBean pageBean;
	private int page = 1;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	private String queryStartDate;
	private String queryEndDate;
	private String queryDevice;
	private String queryRepairStatus;
	private List<JYUser> userList;
	private List<JYConstant> cabTypeList;
	private int unhandledTag = 0;//全部
	private JYAlarm alarmTemp;
	private String orderColumn = "alarm.date";
	

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
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

	public DataList getDataList() {
		return dataList;
	}

	public void setDataList(DataList dataList) {
		this.dataList = dataList;
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

	public String getQueryUser() {
		return queryUser;
	}

	public void setQueryUser(String queryUser) {
		this.queryUser = queryUser;
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

	public List<JYUser> getUserList() {
		return userList;
	}

	public void setUserList(List<JYUser> userList) {
		this.userList = userList;
	}

	public List<JYConstant> getCabTypeList() {
		return cabTypeList;
	}

	public void setCabTypeList(List<JYConstant> cabTypeList) {
		this.cabTypeList = cabTypeList;
	}

	public List<Integer> getPageNumberList() {
		pageNumberList.clear();
		pageNumberList.add(10);
		pageNumberList.add(20);
		pageNumberList.add(30);
		pageNumberList.add(40);
		return pageNumberList;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}
	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	public void setAlarmService(JYAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}


	public String listAlarmAction() throws Exception {
		userList = dataList.getUser();
		cabTypeList = dataList.getCabTpyeConstant();
		final String hql = this.createSql();
		//final String hql = "from JYAlarm alarm where alarm.device.cabinet.user.username like '%%%' ORDER BY id DESC";
		this.pageBean = this.alarmService.getPerPage(pageList, page, hql);
		//page = 1;
		return SUCCESS;
	}

	public String createSql(){
		String orderStr = "";
		if (orderColumn.equals("alarm.date")){
			orderStr = "ORDER BY alarm.date DESC";
		}
		else 
			orderStr = "ORDER BY "+orderColumn;
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(alarm.device.cabinet.user.username = '"+LoginStatus.getUsername()+"' or alarm.device.cabinet.user.username = '--') and ";
		}
		String hql = "from JYAlarm alarm where "+temp;
		if (unhandledTag==1){
			queryLine = null;queryNumber=null;queryType=null;queryUser=null;queryStartDate=null;queryEndDate=null;
			queryRepairStatus = "0";
		}
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUser == null || queryUser.length() == 0)
			queryUser = "%";
		if (queryStartDate == null || queryStartDate.length() == 0)
			queryStartDate = "1000-01-01";
		if (queryEndDate == null || queryEndDate.length() == 0)
			queryEndDate = "9999-12-12";
		if (queryDevice == null || queryDevice.length() == 0)
			queryDevice = "%";
		if (queryRepairStatus == null || queryRepairStatus.length() == 0)
			queryRepairStatus = "%";
		hql = hql + "alarm.device.cabinet.line.name like '%"
				+ queryLine + "%' and "
				+ "alarm.device.cabinet.cabNumber like '%"
				+ queryNumber + "%' and "
				+ "alarm.device.name like '%" + queryDevice
				+ "%' and " + "alarm.status like '%" + queryRepairStatus
				+ "%' and "
				+ "alarm.device.cabinet.cabType.value like '%"
				+ queryType + "%' and " + "alarm.date>= TO_DATE('"
				+ queryStartDate+" 00:00:00"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "alarm.date <= TO_DATE('" + queryEndDate
				+ " 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "alarm.device.cabinet.user.username like '%"
				+ queryUser + "%'" +
				orderStr;
		unhandledTag = 0;
		return hql;
	}
	public void getUnhanledCountAction(){
		unhandledTag = 1;
		final String hql = this.createSql();
		int count = this.alarmService.getTotalCount(hql);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("unhandledCount", count);
        Constant.flush(dataMap);
	}
	
	public String updateAlarmAction(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String username =  (String) session.getAttribute("username");
		JYAlarm alarm = this.alarmService.findJYAlarmById(this.alarmTemp.getId());
		alarm.setNote(this.alarmTemp.getNote());
		alarm.setRepairUser(username);
		alarm.setStatus("1");
		this.alarmService.updateJYAlarm(alarm);
		return SUCCESS;
	}
}
