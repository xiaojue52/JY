package com.station.account.action;


import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.JYUser;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class DeleteUserAction extends ActionSupport {
	private JYUser user;
	private JYUserService userService;

	public JYUser getUser() {
		return user;
	}

	public void setUser(JYUser user) {
		this.user = user;
	}

	public JYUserService getUserService() {
		return userService;
	}

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings("static-access")
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (!LoginStatus.isLogined())
			return "unlogin";
		userService.removeUser(user);
		return this.SUCCESS;
	}
	
}
