package com.station.dao;

import java.util.List;

import com.station.po.JYAlarmTypeCollect;


public interface JYAlarmTypeCollectDAO {
	public void saveJYAlarmTypeCollect(JYAlarmTypeCollect arg0);

	public void removeJYAlarmTypeCollect(JYAlarmTypeCollect arg0);

	public JYAlarmTypeCollect findJYAlarmTypeCollectById(String id);

	public List<JYAlarmTypeCollect> findJYAlarmTypeCollectByHql(String hql);

	public void updateJYAlarmTypeCollect(JYAlarmTypeCollect arg0);
	
}