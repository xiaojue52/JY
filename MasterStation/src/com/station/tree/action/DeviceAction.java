package com.station.tree.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYDevice;
import com.station.service.JYCabinetService;
import com.station.service.JYDeviceService;


@SuppressWarnings("serial")
public class DeviceAction extends ActionSupport{
	private JYDeviceService deviceService;
	private JYCabinetService cabinetService;
	private JYDevice device;

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}
	public JYDevice getDevice() {
		return device;
	}
	public void setDevice(JYDevice device) {
		this.device = device;
	}

	public void setDeviceService(JYDeviceService deviceService) {
		this.deviceService = deviceService;
	}
	public void showDeviceRecord(){
		device = deviceService.findJYDeviceById(device.getDeviceId());
		device.setAlarm(null);
		device.getCabinet().setAlarm(null);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("device", device);
        //Date date = device.getCreateDate();
        Date date = device.getCreateTime();
        dataMap.put("dateTime", date.toString());
        Constant.flush(dataMap);
	}
	public void addDeviceAction(){
		int data = 0;
		if(this.deviceService.deviceIsExist( device.getPositionNumber(), device.getCabinet().getCabId())){
			data = 0;
		}
		else {
			data = 1;
			Date date = new Date();
			//device.setCreateDate(new java.sql.Date(date.getTime()));
			device.setCreateTime(date);
			device.setCabinet(cabinetService.findJYCabinetById(device.getCabinet().getCabId()));
			deviceService.saveJYDevice(device);
		}
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("data", data);
        Constant.flush(dataMap);
	}
	public void updateDeviceAction(){
		JYDevice device0 = this.deviceService.findJYDeviceById(this.device.getDeviceId());
		device.setAlarm(device0.getAlarm());
		device.setCreateTime(device0.getCreateTime());
		//device.setCabinet(cabinetService.findJYCabinetById(device.getCabinet().getCabId()));
		deviceService.updateJYDevice(device);
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("data", 1);
        Constant.flush(dataMap);
	}
	public void deleteDeviceAction(){
		device = deviceService.findJYDeviceById(device.getDeviceId());
		deviceService.removeJYDevice(device);
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", 1);
        Constant.flush(dataMap);
	}

}
