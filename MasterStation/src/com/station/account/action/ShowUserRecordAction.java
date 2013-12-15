package com.station.account.action;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
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

	public void showUserAction() throws Exception {
		user = this.userService.findUserById(user.getUserId());
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("user", user);
        Constant.flush(dataMap);
	}


}