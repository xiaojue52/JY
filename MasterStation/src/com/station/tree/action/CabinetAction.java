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
	public void addCabinetAction(){
		int data = 0;
		if(this.cabinetService.cabinetIsExist(cabinet.getCabNumber())){
			data = 0;
		}
		else {
			data = 1;
			Date date = new Date();
			cabinet.setCreateTime(date);
			cabinetService.saveJYCabinet(cabinet);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", data);
        Constant.flush(dataMap);
	}
	public void updateCabinetAction(){
		cabinet.setAlarm(this.cabinetService.findJYCabinetById(this.cabinet.getCabId()).getAlarm());
		//cabinet.set
		cabinet.setCreateTime(this.cabinetService.findJYCabinetById(this.cabinet.getCabId()).getCreateTime());
		cabinetService.updateJYCabinet(cabinet);
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", 1);
        Constant.flush(dataMap);
	}
	public void deleteCabinetAction(){
		cabinet = cabinetService.findJYCabinetById(cabinet.getCabId());
		cabinetService.removeJYCabinet(cabinet);
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", 1);
        Constant.flush(dataMap);
	}

}
