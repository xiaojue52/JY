package com.station.socket;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;

@SuppressWarnings("serial")
public class SocketAction extends ActionSupport {
	private String cabinetListStr;
	private static SocketHandler socketHandler;

	public String getCabinetListStr() {
		return cabinetListStr;
	}

	public void setCabinetListStr(String cabinetListStr) {
		this.cabinetListStr = cabinetListStr;
	}
	public static void setSocketHandler(SocketHandler socketHandler){
		SocketAction.socketHandler = socketHandler;
	}
	public void getTempDateAction() {
		//System.out.print("getTempDataAction");
		String[] cabinetList = this.cabinetListStr.split(",");
		if (cabinetList.length==0||cabinetList[0].length()==0)return;
		socketHandler.sendCommandWithCabNumberList(cabinetList);
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("test", cabinetList);
		Constant.flush(dataMap);
	}
}
