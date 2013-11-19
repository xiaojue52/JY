package com.station.constant;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.station.po.JYUser;

public class LoginStatus {
	public static boolean isLogined(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		if (session.getAttribute("username")==null)
			return false;
		else 
			return true;
	}
	public static void storeUserDataInSession(JYUser user){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", user.getUsername());
		session.setAttribute("userLevel", user.getUserLevel());
		session.setAttribute("userId", user.getUserId());
		//session.setAttribute("password", user.getPassword());
	}
	public static void clearUserDataFromSession(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", null);
		session.setAttribute("userLevel", null);
		session.setAttribute("userId", null);
		//session.setAttribute("password", null);
	}
	public static int checkUserAccess(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userLevel = (String)session.getAttribute("userLevel");
		if (userLevel!=null&&userLevel.equals("super_admin")){
			return 1;
		}
		if (userLevel!=null&&userLevel.equals("user")){
			return 2;
		}
		if (userLevel!=null&&userLevel.equals("com_admin")){
			return 3;
		}
		return -1;
	}
	public static String getUsername(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		return (String) session.getAttribute("username");
	}
}
