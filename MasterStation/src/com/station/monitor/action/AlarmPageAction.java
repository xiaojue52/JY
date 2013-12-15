package com.station.monitor.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.station.pagebean.PageBean;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class AlarmPageAction extends ActionSupport {
	//private UnhandledExceptionService unhandledExceptionService;
	private JYAlarmService alarmService;
	private PageBean pageBean;
	private int page = 1;
	private int pageList = 10;
	private String cabId;
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
		final String hql = "from JYAlarm alarm where alarm.device.cabinet.cabId = '"+cabId+"' ORDER BY alarm.date DESC";
		this.pageBean = this.alarmService.getPerPage(pageList, page, hql);
		//page = 1;
		return SUCCESS;
	}

	public String createSql(){
		String hql = null;		
		return hql;
	}
}
