package com.station.system.action;

import java.util.HashMap;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;

@SuppressWarnings("serial")
public class HeartBeatAction extends ActionSupport {
	private int heartBeatTime;
	
	public int getHeartBeatTime() {
		return heartBeatTime;
	}
	public void setHeartBeatTime(int heartBeatTime) {
		this.heartBeatTime = heartBeatTime;
	}
	public void updateHeartBeatTimeAction(){
		
		Map<String,Object> dataMap = new HashMap<String,Object>();
		int ret = saveHeartBeatTime();
        dataMap.put("data", ret);
        Constant.flush(dataMap);
	}
	private int saveHeartBeatTime(){
		int ret = 0;
		String path = ServletActionContext.getServletContext().getRealPath("/")+"files/Config.xml";
		
		if(Constant.updateConfig(path, "heartBeatTime", String.valueOf(heartBeatTime))){
			ret = 1;
			Constant.HEARTBEATTIME = heartBeatTime;
		}
		else ret = 0;
		return ret;
	}
}
