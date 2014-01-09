package com.station.monitor.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.constant.LoginStatus;
import com.station.data.DataList;
import com.station.pagebean.PageBean;
import com.station.po.JYConstant;
import com.station.po.JYUserGroup;
import com.station.service.JYMonitorService;

@SuppressWarnings("serial")
public class CabinetStatusAction extends ActionSupport {
	private JYMonitorService monitorService;
	private PageBean pageBean;
	private int page = 1;
    private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	private DataList dataList;
	private List<JYUserGroup> userGroupList;
	private List<JYConstant> cabTypeList;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUserGroup;
	private String orderColumn = "cabinet.cabId";
	

	public String getQueryUserGroup() {
		return queryUserGroup;
	}

	public void setQueryUserGroup(String queryUserGroup) {
		this.queryUserGroup = queryUserGroup;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public DataList getDataList() {
		return dataList;
	}

	public void setDataList(DataList dataList) {
		this.dataList = dataList;
	}

	public List<JYUserGroup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<JYUserGroup> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public List<JYConstant> getCabTypeList() {
		return cabTypeList;
	}

	public void setCabTypeList(List<JYConstant> cabTypeList) {
		this.cabTypeList = cabTypeList;
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


	public JYMonitorService getMonitorService() {
		return monitorService;
	}

	public void setMonitorService(JYMonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
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

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getCabinetStatusAction() throws Exception{
		userGroupList = dataList.getAllUserGroups();
		cabTypeList = dataList.getCabTpyeConstant();
		final String hql = this.createSql();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("queryLine", "%"+queryLine+"%");
		parameters.put("queryNumber", "%"+queryNumber+"%");
		parameters.put("queryType", "%"+queryType+"%");
		parameters.put("queryUserGroup", "%"+queryUserGroup+"%");
		this.pageBean = monitorService.getPerPage(pageList, page, hql,parameters);		
		return "success";
	}

	public String createSql(){
		String orderStr = "";
		if (orderColumn.equals("cabinet.cabId")){
			orderStr = "ORDER BY to_number(replace(cabinet.cabId,'Cab','')) DESC";
		}
		else
			orderStr = "ORDER BY "+orderColumn;
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(cabinet.userGroup.groupName = '"+Constant.getSessionStringAttr("userGroup")+"' or cabinet.userGroup.groupName = '--') and ";
		}
		String hql = "from JYCabinet cabinet where "+temp;
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUserGroup == null || queryUserGroup.length() == 0)
			queryUserGroup = "%";
		hql = hql + "cabinet.line.name like :queryLine and "
		+ "cabinet.cabNumber like :queryNumber and cabinet.cabType.value like :queryType and " 
		+ "cabinet.userGroup.groupName like :queryUserGroup and cabinet.tag = 1 "+orderStr;
		return hql;
	}
	
}
