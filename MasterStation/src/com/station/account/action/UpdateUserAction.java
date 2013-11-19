package com.station.account.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYUser;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class UpdateUserAction extends ActionSupport {
	private JYUser user;
	private String password;
	private String newPassword;
	private Integer resetPassword = 0;
	private int ret = 0;

	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public Integer getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(Integer resetPassword) {
		this.resetPassword = resetPassword;
	}

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

	private JYUserService userService;
	
	public JYUserService getUserService() {
		return userService;
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
	
	public String updateUser() throws Exception {
		// TODO Auto-generated method stub
		if (resetPassword==0){
			user.setPassword(userService.findUserById(user.getUserId()).getPassword());
		}else
			user.setPassword("000000");
		userService.updateUser(user);
		return "users";
	}
	public String updatePassword() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String userId = (String) session.getAttribute("userId");
		user = userService.findUserById(userId);
		//user.setUserLevel(user_tag);
		if (user.getPassword().equals(password)){
			user.setPassword(newPassword);
			userService.updateUser(user);
			ret = 0;
			return "account";
		}else{
			ret = -1;
			return "passworderror";
		}
	}
}
