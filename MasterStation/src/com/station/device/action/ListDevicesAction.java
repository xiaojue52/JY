package com.station.device.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.Device;
import com.station.service.DeviceService;

@SuppressWarnings("serial")
public class ListDevicesAction extends ActionSupport {
	private List<Device> list;
	private DeviceService deviceService;

	public DeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public List<Device> getList() {
		return list;
	}
	public void setList(List<Device> list) {
		this.list = list;
	}

	@Override
	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		list = deviceService.findAllDevice();
		return SUCCESS;
	}
}
