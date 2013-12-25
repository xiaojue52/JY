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
	
	public static void initData(JYUser user){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", user.getUsername());
		session.setAttribute("userLevel", user.getUserLevel());
		session.setAttribute("userId", user.getUserId());
		session.setAttribute("isFirstLogin", user.getIsFirstLogin());
		//session.setAttribute("password", user.getPassword());
		session.setAttribute("topContent", Constant.TOPNAME);
		session.setAttribute("bottomContent", Constant.BOTTOMNAME);
		session.setAttribute("imagePath", Constant.LOGIMAGEPATH);
		session.setAttribute("mesUser", Constant.MESUSER);
		session.setAttribute("mesDate", Constant.MESDATE);
		session.setAttribute("functionNum", Constant.FUNCTIONNUM);
	}
	public static void destroyData(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		session.setAttribute("username", null);
		session.setAttribute("userLevel", null);
		session.setAttribute("userId", null);
		session.setAttribute("isFirstLogin", null);
		session.invalidate();
		//session.setAttribute("password", null);
	}
	public static int checkUserAccess(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		String userLevel = (String)session.getAttribute("userLevel");
		if (userLevel!=null&&userLevel.equals("super_admin")){
			return 1;
		}
		if (userLevel!=null&&userLevel.equals("user")){
			return 3;
		}
		if (userLevel!=null&&userLevel.equals("com_admin")){
			return 2;
		}
		return -1;
	}
	public static String getUsername(){
		HttpSession session = ServletActionContext.getRequest ().getSession();
		return (String) session.getAttribute("username");
	}
}
