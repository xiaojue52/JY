package com.station.account.action;


import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.JYUser;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class AddUserAction extends ActionSupport {

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
		this.userService.saveUser(this.user);
		return SUCCESS;
	}
	
	public String test(){
		return "success";
	}
}