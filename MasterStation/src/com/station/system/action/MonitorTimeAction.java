package com.station.system.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYConstant;
import com.station.service.JYConstantService;
import com.station.socket.SocketRoute;


@SuppressWarnings("serial")
public class MonitorTimeAction extends ActionSupport {
	private JYConstantService constantService;
	private JYConstant constant;
	private static SocketRoute socketRoute;

	public static void setSocketRoute(SocketRoute socketRoute) {
		MonitorTimeAction.socketRoute = socketRoute;
	}
	public JYConstant getConstant() {
		return constant;
	}
	public void setConstant(JYConstant constant) {
		this.constant = constant;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
	public void updateMonitorTimeAction(){
		String hql = "from JYConstant constant where constant.value = '"+constant.getValue()+"'";
		List<JYConstant> list = this.constantService.findJYConstantByHql(hql);
		int ret;
		if (list.size()==0){
			ret = 0;
		}
		else{
			JYConstant arg = list.get(0);
			arg.setSubValue(constant.getSubValue());
			constantService.updateJYConstant(arg);
			socketRoute.sendCommandToSetMonitorTime(constant.getValue(),constant.getSubValue());
			ret = 1;
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
	public void getMonitorTimeAction(){
		String hql = "from JYConstant constant where constant.value = '"+constant.getValue()+"'";
		List<JYConstant> list = this.constantService.findJYConstantByHql(hql);
		String ret;
		if (list.size()==0){
			ret = "0";
		}
		else{
			JYConstant arg = list.get(0);
			ret = arg.getSubValue();
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
}
