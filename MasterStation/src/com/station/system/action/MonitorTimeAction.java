package com.station.system.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYConstant;
import com.station.service.JYConstantService;


@SuppressWarnings("serial")
public class MonitorTimeAction extends ActionSupport {
	private JYConstantService constantService;
	private JYConstant constant;

	public JYConstant getConstant() {
		return constant;
	}
	public void setConstant(JYConstant constant) {
		this.constant = constant;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
	public String updateMonitorTimeAction(){
		String hql = "from JYConstant constant where constant.value = '"+constant.getValue()+"'";
		JYConstant arg = this.constantService.findJYConstantByHql(hql).get(0);
		arg.setSubValue(constant.getSubValue());
		constantService.updateJYConstant(arg);
		return SUCCESS;
	}
}
