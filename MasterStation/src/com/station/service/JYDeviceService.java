package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYDevice;


public interface JYDeviceService {
	public void saveJYDevice(JYDevice arg0);

	public void removeJYDevice(JYDevice arg0);

	public JYDevice findJYDeviceById(String id);

	public List<JYDevice> findJYDeviceByHql(String hql);

	public void updateJYDevice(JYDevice arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
	
	public void removeJYDevices(List<JYDevice> list);
	
	public boolean deviceIsExist(Integer positionNumber, String cabId);
}
