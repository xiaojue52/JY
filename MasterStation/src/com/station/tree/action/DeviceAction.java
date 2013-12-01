package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
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
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson(); 
			String jsonString = gson.toJson(dataMap); 
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String addDeviceAction(){
		device.setCabinet(cabinetService.findJYCabinetById(device.getCabinet().getCabId()));
		deviceService.saveJYDevice(device);
		return SUCCESS;
	}
	public String updateDeviceAction(){
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
