package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.Device;
import com.station.po.UnhandledException;


public interface UnhandledExceptionService {
	public void saveDevice(UnhandledException data);

	public void removeDevice(UnhandledException data);

	public UnhandledException findDeviceById(Integer id);

	public List<UnhandledException> findAllDevice();

	public void updateDevice(UnhandledException data);
	
	public List<UnhandledException> findDevicesByOwner(String owner);
	
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
	
	public UnhandledException getUnhandledException(Device data);
}
