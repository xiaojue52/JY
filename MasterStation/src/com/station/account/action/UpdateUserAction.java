package com.station.account.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.md5.MD5;
import com.station.po.JYUser;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class UpdateUserAction extends ActionSupport {
	private JYUser user;
	private String password;
	private String newPassword;
	private Integer resetPassword = 0;
	private int ret = 0;

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
		JYUser user0 = userService.findUserById(user.getUserId());
		if (resetPassword==0){
			user.setPassword(user0.getPassword());
		}else {
			String str = MD5.CreateMD5String("000000");
			user.setPassword(str);
		}
		user.setIsFirstLogin(user0.getIsFirstLogin());
		userService.updateUser(user);
		return "users";
	}
	public void updatePassword() throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = ServletActionContext.getRequest ().getSession();
		String userId = (String) session.getAttribute("userId");
		user = userService.findUserById(userId);
		//user.setUserLevel(user_tag);
		if (user.getPassword().equals(MD5.CreateMD5String(password))){
			user.setPassword(MD5.CreateMD5String(newPassword));
			userService.updateUser(user);
			ret = 0;
		}else{
			ret = -1;
		}
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("ret", ret);
        Constant.flush(dataMap);
	}
}
