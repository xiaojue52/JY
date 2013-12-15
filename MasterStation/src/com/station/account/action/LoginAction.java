package com.station.account.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.constant.LoginStatus;
import com.station.md5.MD5;
import com.station.po.JYUser;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	
	private List<JYUser> list;
	private JYUserService userService;

	public JYUserService getUserService() {
		return userService;
	}

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}
	private String username;
    private String password;

	public List<JYUser> getList() {
		return list;
	}

	public void setList(List<JYUser> list) {
		this.list = list;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void login(){
		//list = myUserDao.findAllUser();
		int ret = 0;
		if (verify())
			ret = 1;
		else {
			ret = 0;
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
	private boolean verify(){
		String hql = "from JYUser user where user.username != '--'";
		list = userService.findUserByHql(hql);
		JYUser myUser = new JYUser();
		String user;
		String pwd;
		for (int i=0;i<list.size();i++){
			myUser = list.get(i);
			user = myUser.getUsername();
			pwd = myUser.getPassword();
			String str = MD5.CreateMD5String(password);
			if ((username.equals(user))&&(str.equals(pwd))){
				LoginStatus.initData(myUser);
				myUser.setIsFirstLogin(0);
				userService.updateUser(myUser);
				return true;
			}
		}
		return false;
	}
}
