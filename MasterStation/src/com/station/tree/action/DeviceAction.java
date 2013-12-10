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
	public String addDeviceAction(){
		Date date = new Date();
		//device.setCreateDate(new java.sql.Date(date.getTime()));
		device.setCreateTime(date);
		device.setCabinet(cabinetService.findJYCabinetById(device.getCabinet().getCabId()));
		deviceService.saveJYDevice(device);
		return SUCCESS;
	}
	public String updateDeviceAction(){
		device.setCreateTime(this.deviceService.findJYDeviceById(this.device.getDeviceId()).getCreateTime());
		device.setCabinet(cabinetService.findJYCabinetById(device.getCabinet().getCabId()));
		deviceService.updateJYDevice(device);
		return SUCCESS;
	}
	public String deleteDeviceAction(){
		device = deviceService.findJYDeviceById(device.getDeviceId());
		deviceService.removeJYDevice(device);
		return SUCCESS;
	}

}
