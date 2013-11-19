package com.station.dao;

import java.util.List;

import com.station.po.Device;

public interface DeviceDAO {
	public void saveDevice(Device data);

	public void removeDevice(Device data);

	public Device findDeviceById(Integer id);

	public List<Device> findAllDevice();

	public void updateDevice(Device data);

	public List<Device> findDevicesByBox(String box);

	//public List<Device> findDevicesByOwner(String owner);

	public int getTotalCount(String hql);

	public List<Device> getPerPage(final String hql, final int startRow,
			final int countPerpage);

	public Device findDeviceByIdentify(String identify);
}