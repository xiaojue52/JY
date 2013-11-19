package com.station.dao;

import java.util.List;

import com.station.po.JYDevice;


public interface JYDeviceDAO {
	public void saveJYDevice(JYDevice arg0);

	public void removeJYDevice(JYDevice arg0);

	public JYDevice findJYDeviceById(String id);

	public List<JYDevice> findJYDeviceByHql(String hql);

	public void updateJYDevice(JYDevice arg0);
	
	public int getTotalCount(String hql);

	public List<JYDevice> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}