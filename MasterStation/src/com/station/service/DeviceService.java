package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.Device;


public interface DeviceService {
	public void saveDevice(Device data);

	public void removeDevice(Device data);

	public Device findDeviceById(Integer id);

	public List<Device> findAllDevice();

	public void updateDevice(Device data);
	
	public List<Device> findDevicesByBox(String box);
	
	//public List<Device> findDevicesByOwner(String owner);
	
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
	
	public Device findDeviceByIdentify(String identify);
}
