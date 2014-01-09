package com.station.account.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.pagebean.PageBean;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class ListUsersAction extends ActionSupport {

	private JYUserService userService;
	private int page = 1;
	private PageBean pageBean;
	private int code;
	private int ret;
	private String username;
	private String company;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	private String orderColumn = "user.userId";
	

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("username", "%"+username+"%");
		parameters.put("company", "%"+company+"%");
		this.pageBean = userService.getPerPage(pageList, page, hql,parameters);
		if (code == -1){
			ret = -1;
		}else if(code == -2){
			ret = -2;
		}else
			ret = 0;
		code = 0;
		return SUCCESS;
	}

	public String createSql() {
		String orderStr = "";
		if (orderColumn.equals("user.userId")){
			orderStr = "order by to_number(replace(user.userId,'YH','')) desc";
		}
		else
			orderStr = "order by "+orderColumn;
		String hql = "from JYUser user where (user_level = 'user' or user_level = 'com_admin') and ";
		if (username==null||username.length()==0)
			username = "%";
		if (company==null||company.length()==0)
			company = "%";
		hql = hql+"user.username like :username and user.company like :company "+orderStr;		
		return hql;
	}
}
