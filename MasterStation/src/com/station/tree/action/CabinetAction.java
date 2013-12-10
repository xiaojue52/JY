package com.station.tree.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYCabinet;
import com.station.service.JYCabinetService;


@SuppressWarnings("serial")
public class CabinetAction extends ActionSupport{
	private JYCabinetService cabinetService;

	private JYCabinet cabinet;

	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
	}

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}
	public void showCabinetRecord(){
		cabinet = cabinetService.findJYCabinetById(cabinet.getCabId());
		cabinet.setAlarm(null);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("cabinet", cabinet);
        Date date = cabinet.getCreateTime();
        dataMap.put("dateTime", date.toString());
        Constant.flush(dataMap);
	}
	public String addCabinetAction(){
		Date date = new Date();
		cabinet.setCreateTime(date);
		cabinetService.saveJYCabinet(cabinet);
		return SUCCESS;
	}
	public String updateCabinetAction(){
		
		cabinet.setCreateTime(this.cabinetService.findJYCabinetById(this.cabinet.getCabId()).getCreateTime());
		cabinetService.updateJYCabinet(cabinet);
		return SUCCESS;
	}
	public String deleteCabinetAction(){
		cabinet = cabinetService.findJYCabinetById(cabinet.getCabId());
		cabinetService.removeJYCabinet(cabinet);
		return SUCCESS;
	}

}
