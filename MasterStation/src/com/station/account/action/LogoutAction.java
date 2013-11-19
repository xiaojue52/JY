package com.station.account.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport {
	@SuppressWarnings("static-access")
	public String logout(){
		LoginStatus.clearUserDataFromSession();
		//System.out.print("logout");
		return this.SUCCESS;
	}
}
