package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYDevice;


public interface JYDeviceDAO {
	public void saveJYDevice(JYDevice arg0);

	public void removeJYDevice(JYDevice arg0);

	public JYDevice findJYDeviceById(String id);

	public List<JYDevice> findJYDeviceByHql(String hql);

	public void updateJYDevice(JYDevice arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYDevice> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}