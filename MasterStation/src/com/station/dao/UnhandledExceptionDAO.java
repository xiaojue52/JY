package com.station.dao;

import java.util.List;
import com.station.po.UnhandledException;


public interface UnhandledExceptionDAO {
	public void saveDevice(UnhandledException data);

	public void removeDevice(UnhandledException data);

	public UnhandledException findDeviceById(Integer id);

	public List<UnhandledException> findAllDevice();

	public void updateDevice(UnhandledException data);
	
	public List<UnhandledException> findDevicesByOwner(String owner);
	
	public int getTotalCount(String hql);
	
	public List<UnhandledException> getPerPage(final String hql,final int startRow,final int countPerpage);
}