package com.station.dao;

import java.util.List;

import com.station.po.DeviceHistory;

public interface DeviceHistoryDAO {
	public void saveDevice(DeviceHistory data);

	public void removeDevice(DeviceHistory data);

	public DeviceHistory findDeviceById(Integer id);

	public List<DeviceHistory> findAllDevice();

	public void updateDevice(DeviceHistory data);
	
	public List<DeviceHistory> findDevicesByLine(String line, String owner);
	
	public List<DeviceHistory> findDevicesByOwner(String owner);
	
	public int getTotalCount(String hql);
	
	public List<DeviceHistory> getPerPage(final String hql,final int startRow,final int countPerpage);
}