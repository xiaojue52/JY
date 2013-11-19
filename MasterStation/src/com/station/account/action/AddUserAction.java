package com.station.account.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
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
		String hql = "from JYUser user where user.username = '"+user.getUsername()+"'";
		List<JYUser> list = this.userService.findUserByHql(hql);
		if (list.size()>0){
			return ERROR;
		}else{
			this.userService.saveUser(this.user);
			return SUCCESS;
		}
	}
}