package com.station.datahandler.chart.datapage.action;

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
import com.station.service.JYDeviceService;

@SuppressWarnings("serial")
public class DeviceCompareAction extends ActionSupport {
	private DataList dataList;
	private JYDeviceService deviceService;
	private PageBean pageBean;
	private int page = 1;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUserGroup;
	private List<JYUserGroup> userGroupList;
	private List<JYConstant> cabTypeList;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

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

	public List<JYConstant> getCabTypeList() {
		return cabTypeList;
	}

	public void setCabTypeList(List<JYConstant> cabTypeList) {
		this.cabTypeList = cabTypeList;
	}


	public String getQueryUserGroup() {
		return queryUserGroup;
	}

	public void setQueryUserGroup(String queryUserGroup) {
		this.queryUserGroup = queryUserGroup;
	}

	public List<JYUserGroup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<JYUserGroup> userGroupList) {
		this.userGroupList = userGroupList;
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

	public void setDeviceService(JYDeviceService deviceService) {
		this.deviceService = deviceService;
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

	public String listAction() throws Exception {
		userGroupList = dataList.getAllUserGroups();
		cabTypeList = dataList.getCabTpyeConstant();
		final String hql = this.createSql();
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("queryLine", "%"+queryLine+"%");
		parameters.put("queryNumber", "%"+queryNumber+"%");
		parameters.put("queryType", "%"+queryType+"%");
		parameters.put("queryUserGroup", "%"+queryUserGroup+"%");
		//final String hql = "from JYDevice device where tag = 1";
		//final String hql = "select t.collect_time,wm_concat(t.detector_id), wm_concat(d.device_id), wm_concat(de.cab_id)from jy_history t, jy_detector d,jy_device de,jy_cabinet c where d.detector_id = t.detector_id and de.cab_id = c.cab_id and d.device_id = de.device_id group by  t.collect_time";
		this.pageBean = deviceService.getPerPage(pageList, page, hql,parameters);
		return SUCCESS;
	}

	public String createSql() {
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(device.cabinet.userGroup.groupName = '"+Constant.getSessionStringAttr("userGroup")+"' or device.cabinet.userGroup.groupName = '--') and ";
		}
		String hql = "from JYDevice device where "+temp;
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUserGroup == null || queryUserGroup.length() == 0)
			queryUserGroup = "%";
		hql = hql + "device.cabinet.line.name like :queryLine and "
				+ "device.cabinet.cabNumber like :queryNumber and "
				+ "device.cabinet.cabType.value like :queryType and " 
				+ "device.cabinet.userGroup.groupName like :queryUserGroup and tag = 1 order by to_number(replace(device.deviceId,'Device','')) desc";
		return hql;
	}

}
