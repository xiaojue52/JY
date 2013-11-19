package com.station.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.station.account.action.LoginAction;

@SuppressWarnings("serial")
public class UserLoginInterceptor extends AbstractInterceptor {

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		if (LoginAction.class == arg0.getAction().getClass()){
			return arg0.invoke();
		}
		Map map = arg0.getInvocationContext().getSession();
		if (null == map.get("username"))
			return Action.LOGIN;
		return arg0.invoke();
	}

}
