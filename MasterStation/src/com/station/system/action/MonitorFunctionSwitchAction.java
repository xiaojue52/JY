package com.station.system.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;

@SuppressWarnings("serial")
public class MonitorFunctionSwitchAction extends ActionSupport {
	private int functionNum;
	
	public int getFunctionNum() {
		return functionNum;
	}

	public void setFunctionNum(int functionNum) {
		this.functionNum = functionNum;
	}

	public void updateMonitorFunctionAction(){
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		int ret = saveFunctionNumber();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
	private int saveFunctionNumber(){
		int ret = 0;
		String path = ServletActionContext.getServletContext().getRealPath("/")+"files/Config.xml";
		
		if(Constant.updateConfig(path, "functionNum", String.valueOf(functionNum))){
			ret = 1;
			Constant.FUNCTIONNUM = String.valueOf(functionNum);
		}
		else ret = 0;
		return ret;
	}
}
