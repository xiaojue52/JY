package com.station.account.action;


import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.JYUser;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class ShowUserRecordAction extends ActionSupport {

	private JYUser user;
	private JYUserService userService;

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	public JYUser getUser() {
		return user;
	}

	public void setUser(JYUser user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		HttpSession session = ServletActionContext.getRequest ().getSession();
		if (session.getAttribute("Tag")!=null&&session.getAttribute("Tag").equals("user"))
			return "failue";
		user = this.userService.findUserById(user.getUserId());
		return SUCCESS;
	}


}