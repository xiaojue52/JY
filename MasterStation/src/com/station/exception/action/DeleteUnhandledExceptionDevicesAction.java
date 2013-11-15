package com.station.exception.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.po.UnhandledException;
import com.station.service.UnhandledExceptionService;

@SuppressWarnings("serial")
public class DeleteUnhandledExceptionDevicesAction extends ActionSupport {
	private UnhandledException unhandledException;
	private UnhandledExceptionService unhandledExceptionService;


	public UnhandledException getUnhandledException() {
		return unhandledException;
	}

	public void setUnhandledException(UnhandledException unhandledException) {
		this.unhandledException = unhandledException;
	}

	public void setUnhandledExceptionService(
			UnhandledExceptionService unhandledExceptionService) {
		this.unhandledExceptionService = unhandledExceptionService;
	}

	@Override
	public String execute() throws Exception {
		if (!LoginStatus.isLogined())
			return "unlogin";
		unhandledExceptionService.removeDevice(unhandledException);
		return SUCCESS;
	}
}
