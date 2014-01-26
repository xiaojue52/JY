/**
 * 此功能该版本未实现
 * 欠费提醒设置类
 */
package com.station.system.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;

@SuppressWarnings("serial")
public class MessageAction extends ActionSupport {
	private int dateNumber;
	private String username;
	
	public int getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(int dateNumber) {
		this.dateNumber = dateNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void updateMessageRevicerAction(){
		int ret = saveMessageReciveConfig();
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
	private int saveMessageReciveConfig(){
		int ret = 0;
		String path = ServletActionContext.getServletContext().getRealPath("/")+"files/Config.xml";		
		if(Constant.updateConfig(path, "mesDate", String.valueOf(dateNumber))&&Constant.updateConfig(path, "mesUser", username)){
			ret = 1;
			Constant.MESDATE = String.valueOf(dateNumber);
			Constant.MESUSER = username;
		}
		else ret = 0;
		return ret;
	}
}
