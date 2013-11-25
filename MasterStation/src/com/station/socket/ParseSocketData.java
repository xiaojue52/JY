package com.station.socket;

import java.util.Date;

import com.station.constant.ParseStringToDecimal;
import com.station.po.Device;
import com.station.po.DeviceHistory;
import com.station.po.UnhandledException;
import com.station.service.DeviceHistoryService;
import com.station.service.UnhandledExceptionService;

public class ParseSocketData {
	public static String exception = "异常";
	/*public static String normal = "正常";
	public static String hTemp = "温度过高";
	public static String dNormal = "设备正常";
	public static String offLine = "设备离线";
	private static DeviceService deviceService;
	private static DeviceHistoryService deviceHistoryService;
	private static UnhandledExceptionService unhandledExceptionService;

	public static void setDeviceService(DeviceService service) {
		ParseSocketData.deviceService = service;
	}

	public static void setDeviceHistoryService(
			DeviceHistoryService deviceHistoryService) {
		ParseSocketData.deviceHistoryService = deviceHistoryService;
	}

	public static void setUnhandledExceptionService(
			UnhandledExceptionService unhandledExceptionService) {
		ParseSocketData.unhandledExceptionService = unhandledExceptionService;
	}
	public static void updateDevice(String identify, String currentData){
		double temp = 0;
		if (ParseStringToDecimal.IsDouble(currentData)){
			temp = Double.valueOf(currentData);
		}
		Device device = deviceService
		.findDeviceByIdentify(identify);
		if (device != null) {
			device.setCurrentData(temp);
			Date date = new Date();
			device.setCreateDate(new java.sql.Date(date.getTime()));
			device.setCreateTime(new java.sql.Time(date.getTime()));
			double wTemp = device.getWarningTemperature();
			if (temp>=wTemp){
				device.setReason(hTemp);
				device.setStatus(exception);
				UnhandledException unhandledException = unhandledExceptionService.getUnhandledException(device);
				unhandledExceptionService.saveDevice(unhandledException);
			}
			else{
				device.setReason(dNormal);
				device.setStatus(normal);

			}
			deviceService.updateDevice(device);
	
			DeviceHistory deviceHistory = deviceHistoryService.getDeviceHistory(device);
			deviceHistoryService.saveDevice(deviceHistory);
		}
		
	}
	public static void deviceOffline(String identify){
		Device device = deviceService
		.findDeviceByIdentify(identify);
		if (device != null) {
			device.setStatus(exception);
			device.setReason(offLine);
			deviceService.updateDevice(device);
			UnhandledException unhandledException = unhandledExceptionService.getUnhandledException(device);
			unhandledExceptionService.saveDevice(unhandledException);
		}
	}
	public static boolean CheckDevice(String loginMes){
		String command[] = loginMes.split("|");
		if (command!=null&&command.length==3){
			return false;
		}
		else
			return false;
	}*/
}
