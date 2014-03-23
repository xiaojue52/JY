/**
 * 查看实时数据类
 */
package com.station.socket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;

@SuppressWarnings("serial")
public class SocketAction extends ActionSupport {
	private String cabinetListStr;
	private static SocketRoute socketRoute;

	public String getCabinetListStr() {
		return cabinetListStr;
	}

	public void setCabinetListStr(String cabinetListStr) {
		this.cabinetListStr = cabinetListStr;
	}
	public static void setSocketRoute(SocketRoute socketRoute){
		SocketAction.socketRoute = socketRoute;
	}
	public void getTempDataAction() {
		//System.out.print("getTempDataAction");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String[] cabinetList = this.cabinetListStr.split(",");
		/*if (cabinetList.length==0||cabinetList[0].length()==0)dataMap.put("data", 0);
		List<String> list =socketRoute.sendCommandToGetTempWithCabIdList(cabinetList);
		
		if(list==null){
			dataMap.put("data", 1);
		}else
			dataMap.put("data", list);*/
		dataMap.put("data", 1);
		Constant.flush(dataMap);
	}
}
