package com.station.exception.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.service.UnhandledExceptionService;
import com.tation.query.column.SqlQueryColumn;

@SuppressWarnings("serial")
public class ListUnhandledExceptionDevicesAction extends ActionSupport {
	private UnhandledExceptionService unhandledExceptionService;
	private SqlQueryColumn sqlUnHandledExceptionColumn;
	private PageBean pageBean;
	private int page = 1;

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

	public void setUnhandledExceptionService(
			UnhandledExceptionService unhandledExceptionService) {
		this.unhandledExceptionService = unhandledExceptionService;
	}

	@Override
	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		final String hql = this.createSql();
		this.pageBean = unhandledExceptionService.getPerPage(10, page, hql);
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
