package com.station.account.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport {
	@SuppressWarnings("static-access")
	public String logout(){
		LoginStatus.destroyData();
		//System.out.print("logout");aaa
		return this.SUCCESS;
	}
}
