package com.station.service.impl;

import java.util.Map;

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
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return this.cabinetService.getPerPage(countPerpage, currentPage, hql,parameters);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return cabinetService.getTotalCount(hql,parameters);
	}
}
