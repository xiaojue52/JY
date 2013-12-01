package com.station.alarm.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.query.column.SqlQueryColumn;
import com.station.service.JYAlarmService;

@SuppressWarnings("serial")
public class AlarmAction extends ActionSupport {
	//private UnhandledExceptionService unhandledExceptionService;
	private JYAlarmService alarmService;
	private SqlQueryColumn sqlUnHandledExceptionColumn;
	private PageBean pageBean;
	private int page = 1;
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

	public void setAlarmService(JYAlarmService alarmService) {
		this.alarmService = alarmService;
	}

	public SqlQueryColumn getSqlUnHandledExceptionColumn() {
		return sqlUnHandledExceptionColumn;
	}

	public void setSqlUnHandledExceptionColumn(
			SqlQueryColumn sqlUnHandledExceptionColumn) {
		this.sqlUnHandledExceptionColumn = sqlUnHandledExceptionColumn;
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
		final String hql = "from JYAlarm alarm";
		this.pageBean = this.alarmService.getPerPage(pageList, page, hql);
		//page = 1;
		return SUCCESS;
	}

	public String createSql(){
		String hql;
		String query = null;
		
		if (sqlUnHandledExceptionColumn!=null)	
			query = sqlUnHandledExceptionColumn.getQueryString();
			
		if(LoginStatus.checkUserAccess()==1){
			if (query==null)
				hql="from UnhandledException unhandledException";
			else
				hql="from UnhandledException unhandledException "+query;
		}else if (query==null)
			hql="from UnhandledException unhandledException where owner = '"+LoginStatus.getUsername()+"'";
		else
			hql="from UnhandledException unhandledException "+query+" and owner = '"+LoginStatus.getUsername()+"'";
		sqlUnHandledExceptionColumn = null;
		return hql;
	}
}
