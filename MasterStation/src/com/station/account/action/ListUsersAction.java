package com.station.account.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.service.JYUserService;
import com.tation.query.column.SqlQueryColumn;

@SuppressWarnings("serial")
public class ListUsersAction extends ActionSupport {

	private JYUserService userService;
	private SqlQueryColumn sqlUserColumn;
	private int page = 1;
	private PageBean pageBean;

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	public SqlQueryColumn getSqlUserColumn() {
		return sqlUserColumn;
	}

	public void setSqlUserColumn(SqlQueryColumn sqlUserColumn) {
		this.sqlUserColumn = sqlUserColumn;
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

	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		String hql = this.createSql();
		if(hql == null)
			return "unlogin";
		this.pageBean = userService.getPerPage(10, page, hql);
		return SUCCESS;
	}

	public String createSql() {
		String hql;
		String query = null;

		if (sqlUserColumn != null)
			query = sqlUserColumn.getQueryString();

		if (LoginStatus.checkUserAccess()==1) {
			if (query == null)
				hql = "from JYUser user where (user_level = 'user' or user_level = 'com_admin') order by id desc";
			else
				hql = "from JYUser user " + query +" and (user_level = 'user' or user_level = 'com_admin') order by id desc";
		} else
			hql = null;
		sqlUserColumn = null;
		return hql;
	}
}
