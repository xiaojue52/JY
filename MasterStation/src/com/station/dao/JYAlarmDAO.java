package com.station.dao;

import java.util.List;

import com.station.po.JYAlarm;


public interface JYAlarmDAO {
	public void saveJYAlarm(JYAlarm arg0);

	public void removeJYAlarm(JYAlarm arg0);

	public JYAlarm findJYAlarmById(String id);

	public List<JYAlarm> findJYAlarmByHql(String hql);

	public void updateJYAlarm(JYAlarm arg0);
	
	public int getTotalCount(String hql);

	public List<JYAlarm> getPerPage(final String hql,final int startRow,final int countPerpage);
	
	public void removeMultiplAlarms(String hql);
	
	public List<?> findCostomizeObjHql(String hql);
	
}