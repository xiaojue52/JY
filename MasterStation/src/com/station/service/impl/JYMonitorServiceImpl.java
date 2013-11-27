package com.station.service.impl;

import com.station.pagebean.PageBean;
import com.station.service.JYCabinetService;
import com.station.service.JYMonitorService;

public class JYMonitorServiceImpl implements JYMonitorService {

	private JYCabinetService cabinetService;


	public JYCabinetService getCabinetService() {
		return cabinetService;
	}

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		return this.cabinetService.getPerPage(countPerpage, currentPage, hql);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return cabinetService.getTotalCount(hql);
	}
}
