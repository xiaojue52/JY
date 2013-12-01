package com.station.account.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.pagebean.PageBean;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class ListUsersAction extends ActionSupport {

	private JYUserService userService;
	private int page = 1;
	private PageBean pageBean;
	private int errorCode;
	private int ret;
	private String username;
	private String company;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public JYUserService getUserService() {
		return userService;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public void setUserService(JYUserService userService) {
		this.userService = userService;
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
		String hql = this.createSql();
		this.pageBean = userService.getPerPage(100, page, hql);
		if (errorCode == -1){
			ret = -1;
		}else if(errorCode == -2){
			ret = -2;
		}else
			ret = 0;
		errorCode = 0;
		return SUCCESS;
	}

	public String createSql() {
		String hql = "from JYUser user where (user_level = 'user' or user_level = 'com_admin') and ";
		if (username==null||username.length()==0)
			username = "%";
		if (company==null||company.length()==0)
			company = "%";
		hql = hql+"user.username like '%"+username+"%' "+"and user.company like '%"+company+"%' order by id desc";		
		return hql;
	}
}
