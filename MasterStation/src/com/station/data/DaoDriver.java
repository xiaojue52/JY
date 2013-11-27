package com.station.data;
import com.station.service.DeviceHistoryService;
import com.station.service.UnhandledExceptionService;
import com.station.servlet.GetDateByServlet;
import com.station.socket.ParseSocketData;

public class DaoDriver {
	//private DeviceService deviceService;
	private UnhandledExceptionService unhandledExceptionService;
	private DeviceHistoryService deviceHistoryService;

	public void setUnhandledExceptionService(
			UnhandledExceptionService unhandledExceptionService) {
		this.unhandledExceptionService = unhandledExceptionService;
		GetDateByServlet.setUnhandledExceptionService(this.unhandledExceptionService);
		//ParseSocketData.setUnhandledExceptionService(this.unhandledExceptionService);
	}

	//public void setDeviceService(DeviceService deviceService) {
		//this.deviceService = deviceService;
		//ParseSocketData.setDeviceService(this.deviceService);
	//}

	public void setDeviceHistoryService(DeviceHistoryService deviceHistoryService) {
		this.deviceHistoryService = deviceHistoryService;
		//ParseSocketData.setDeviceHistoryService(this.deviceHistoryService);
	}
	
}
