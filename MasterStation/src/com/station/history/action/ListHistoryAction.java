package com.station.history.action;


import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.query.column.SqlQueryColumn;
import com.station.service.DeviceHistoryService;


@SuppressWarnings("serial")
public class ListHistoryAction extends ActionSupport {
	private DeviceHistoryService deviceHistoryService;
	private PageBean pageBean;
	private int page = 1;
	private SqlQueryColumn sqlDeviceHistoryColumn;
	

	public void setSqlDeviceHistoryColumn(
			SqlQueryColumn sqlDeviceHistoryColumn) {
		this.sqlDeviceHistoryColumn = sqlDeviceHistoryColumn;
	}
	
	public SqlQueryColumn getSqlDeviceHistoryColumn() {
		return sqlDeviceHistoryColumn;
	}

	public DeviceHistoryService getDeviceHistoryService() {
		return deviceHistoryService;
	}
	public void setDeviceHistoryService(DeviceHistoryService deviceHistoryService) {
		this.deviceHistoryService = deviceHistoryService;
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

	public String listHistory() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		final String hql = this.createSql();
		this.pageBean = deviceHistoryService.getPerPage(10, page, hql);
		page = 1;
		return SUCCESS;
	}
	
	public String listExceptionHistory() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		final String hql = this.createSqlException();
		this.pageBean = deviceHistoryService.getPerPage(10, page, hql);
		page = 1;
		return SUCCESS;
	}
	
	public String createSql(){
		String hql;
		String query = null;
		if (sqlDeviceHistoryColumn!=null)	
			query = sqlDeviceHistoryColumn.getQueryString();
			
		if(LoginStatus.checkUserAccess()==1){
			if (query==null)
				hql="from DeviceHistory DeviceHistory" + " ORDER BY id DESC";
			else
				hql="from DeviceHistory DeviceHistory "+query  + " ORDER BY id DESC";
		}else if (query==null)
			hql="from DeviceHistory DeviceHistory where owner = '"+LoginStatus.getUsername()+"'" + " ORDER BY id DESC";
		else
			hql="from DeviceHistory DeviceHistory "+query +" and owner = '"+LoginStatus.getUsername()+"'" + " ORDER BY id DESC";
		sqlDeviceHistoryColumn = null;
		return hql;
	}
	
	public String createSqlException(){
		String hql;
		String query = null;
		if (sqlDeviceHistoryColumn!=null)	
			query = sqlDeviceHistoryColumn.getQueryString();
			
		if(LoginStatus.checkUserAccess()==1){
			if (query==null)
				hql="from DeviceHistory DeviceHistory where status = '异常'" + " ORDER BY id DESC";
			else
				hql="from DeviceHistory DeviceHistory "+query+" and status='异常'" + " ORDER BY id DESC";
		}else if (query==null)
			hql="from DeviceHistory DeviceHistory where owner = '"+LoginStatus.getUsername()+"' and status='异常'" + " ORDER BY id DESC";
		else
			hql="from DeviceHistory DeviceHistory "+query +" and owner = '"+LoginStatus.getUsername()+"' and status='异常'" + " ORDER BY id DESC";
		sqlDeviceHistoryColumn = null;
		return hql;
	}
	
}
