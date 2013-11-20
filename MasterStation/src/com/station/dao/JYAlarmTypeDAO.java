package com.station.dao;

import java.util.List;

import com.station.po.JYAlarmType;


public interface JYAlarmTypeDAO {
	public void saveJYAlarmType(JYAlarmType arg0);

	public void removeJYAlarmType(JYAlarmType arg0);

	public JYAlarmType findJYAlarmTypeById(Integer id);

	public List<JYAlarmType> findJYAlarmTypeByHql(String hql);

	public void updateJYAlarmType(JYAlarmType arg0);
	
}