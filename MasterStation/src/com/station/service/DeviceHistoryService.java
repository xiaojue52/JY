package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.DeviceHistory;


public interface DeviceHistoryService {
	public void saveDevice(DeviceHistory data);

	public void removeDevice(DeviceHistory data);

	public DeviceHistory findDeviceById(Integer id);

	public List<DeviceHistory> findAllDevice();

	public void updateDevice(DeviceHistory data);
	
	public List<DeviceHistory> findDevicesByLine(String line, String owner);
	
	public List<DeviceHistory> findDevicesByOwner(String owner);
	
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);

}
