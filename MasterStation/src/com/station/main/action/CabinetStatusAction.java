package com.station.main.action;


import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.station.data.DataList;
import com.station.pagebean.PageBean;
import com.station.po.JYConstant;
import com.station.po.JYUser;
import com.station.service.JYMonitorService;

@SuppressWarnings("serial")
public class CabinetStatusAction extends ActionSupport {
	private JYMonitorService monitorService;
	private PageBean pageBean;
	private int page = 1;
    private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	private DataList dataList;
	private List<JYUser> userList;
	private List<JYConstant> cabTypeList;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	

	public DataList getDataList() {
		return dataList;
	}

	public void setDataList(DataList dataList) {
		this.dataList = dataList;
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
		userList = dataList.getUser();
		cabTypeList = dataList.getCabTpyeConstant();
		final String hql = this.createSql();
		this.pageBean = monitorService.getPerPage(pageList, page, hql);		
		return "success";
	}

	public String createSql(){
		String hql = "from JYCabinet cabinet where ";
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUser == null || queryUser.length() == 0)
			queryUser = "%";
		hql = hql + "cabinet.line.name like '%"
		+ queryLine + "%' and "
		+ "cabinet.cabNumber like '%"
		+ queryNumber + "%' and "
		+ "cabinet.cabType.value like '%"
		+ queryType + "%' and " 
		+ "cabinet.user.username like '%"
		+ queryUser + "%' and cabinet.tag = 1 ORDER BY id DESC";
		return hql;
	}
	
}
