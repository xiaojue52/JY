package com.station.account.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.md5.MD5;
import com.station.po.JYUser;
import com.station.service.JYUserService;

@SuppressWarnings("serial")
public class UserAction extends ActionSupport {

	private JYUser user;
	private JYUserService userService;
	private String password;
	private String newPassword;
	private Integer resetPassword = 0;
	private int ret = 0;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(Integer resetPassword) {
		this.resetPassword = resetPassword;
	}

	public int getRet() {
		return ret;
	}

	public void setRet(int ret) {
		this.ret = ret;
	}

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	public JYUser getUser() {
		return user;
	}

	public void setUser(JYUser user) {
		this.user = user;
	}

	public String addUserAction() throws Exception {
		String hql = "from JYUser user where user.username = '"
				+ user.getUsername() + "'";
		List<JYUser> list = this.userService.findUserByHql(hql);
		if (list.size() > 0) {
			return ERROR;
		} else {
			String password = MD5.CreateMD5String(this.user.getPassword());
			this.user.setPassword(password);
			this.user.setIsFirstLogin(1);
			this.userService.saveUser(this.user);
			return SUCCESS;
		}
	}

	public String deleteUserAction() throws Exception {
		// TODO Auto-generated method stub
		if (userService.removeUser(user) == 1)
			return SUCCESS;
		else
			return ERROR;
	}

	public void showUserAction() throws Exception {
		user = this.userService.findUserById(user.getUserId());
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("user", user);
		Constant.flush(dataMap);
	}

	public String updateUser() throws Exception {
		// TODO Auto-generated method stub
		JYUser user0 = userService.findUserById(user.getUserId());
		if (resetPassword == 0) {
			user.setPassword(user0.getPassword());
		} else {
			String str = MD5.CreateMD5String("000000");
			user.setPassword(str);
		}
		user.setIsFirstLogin(user0.getIsFirstLogin());
		userService.updateUser(user);
		return "users";
	}

	public void updatePassword() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userId = (String) session.getAttribute("userId");
		user = userService.findUserById(userId);
		// user.setUserLevel(user_tag);
		if (user.getPassword().equals(MD5.CreateMD5String(password))) {
			user.setPassword(MD5.CreateMD5String(newPassword));
			userService.updateUser(user);
			ret = 0;
		} else {
			ret = -1;
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("ret", ret);
		Constant.flush(dataMap);
	}

}