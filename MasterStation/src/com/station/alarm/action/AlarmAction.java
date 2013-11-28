package com.station.alarm.action;

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
		this.pageBean = this.alarmService.getPerPage(10, page, hql);
		page = 1;
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
