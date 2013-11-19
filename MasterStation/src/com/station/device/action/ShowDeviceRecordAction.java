package com.station.device.action;


import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.Device;
import com.station.service.DeviceService;


@SuppressWarnings("serial")
public class ShowDeviceRecordAction extends ActionSupport {

	private Device device;
	private DeviceService deviceService;
	public Device getDevice() {
		return device;
	}
	public void setDevice(Device device) {
		this.device = device;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	@Override
	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		device = this.deviceService.findDeviceById(device.getId());
		return SUCCESS;
	}
}