package com.station.history.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.station.data.DataList;
import com.station.pagebean.PageBean;
import com.station.po.JYConstant;
import com.station.po.JYUser;
import com.station.service.JYCabinetHistoryService;

@SuppressWarnings("serial")
public class HistoryPageAction extends ActionSupport {
	private DataList dataList;
	private JYCabinetHistoryService cabinetHistoryService;
	private PageBean pageBean;
	private int page = 1;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	private String queryStartDate;
	private String queryEndDate;
	private List<JYUser> userList;
	private List<JYConstant> cabTypeList;
	private String cabId;
	
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
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

	public List<JYConstant> getCabTypeList() {
		return cabTypeList;
	}

	public void setCabTypeList(List<JYConstant> cabTypeList) {
		this.cabTypeList = cabTypeList;
	}

	public List<JYUser> getUserList() {
		return userList;
	}

	public void setUserList(List<JYUser> userList) {
		this.userList = userList;
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


	public void setCabinetHistoryService(
			JYCabinetHistoryService cabinetHistoryService) {
		this.cabinetHistoryService = cabinetHistoryService;
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

	public String listHistoryAction() throws Exception {
		userList = dataList.getUser();
		cabTypeList = dataList.getCabTpyeConstant();
		//final String hql = this.createSql();
		final String hql = "from JYCabinetHistory cabinetHistory where cabinetHistory.cabinet.cabId = '"+this.cabId+"' ORDER BY id DESC";
		//final String hql = "select t.collect_time,wm_concat(t.detector_id), wm_concat(d.device_id), wm_concat(de.cab_id)from jy_history t, jy_detector d,jy_device de,jy_cabinet c where d.detector_id = t.detector_id and de.cab_id = c.cab_id and d.device_id = de.device_id group by  t.collect_time";
		this.pageBean = cabinetHistoryService.getPerPage(pageList, page, hql);
		return SUCCESS;
	}

	public String createSql() {
		String hql = "from JYCabinetHistory cabinetHistory where ";
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
		hql = hql + "cabinetHistory.cabinet.line.name like '%"
				+ queryLine + "%' and "
				+ "cabinetHistory.cabinet.cabNumber like '%"
				+ queryNumber + "%' and "
				+ "cabinetHistory.cabinet.cabType.value like '%"
				+ queryType + "%' and " + "cabinetHistory.createDate>= TO_DATE('"
				+ queryStartDate + "','YYYY-MM-DD') and "
				+ "cabinetHistory.createDate <= TO_DATE('" + queryEndDate
				+ "','YYYY-MM-DD') and "
				+ "cabinetHistory.cabinet.user.username like '%"
				+ queryUser + "%' ORDER BY id DESC";
		return hql;
	}

}
