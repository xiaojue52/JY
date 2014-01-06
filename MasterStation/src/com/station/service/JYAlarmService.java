package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYAlarm;


public interface JYAlarmService {
	public void saveJYAlarm(JYAlarm arg0);

	public void removeJYAlarm(JYAlarm arg0);

	public JYAlarm findJYAlarmById(String id);

	public List<JYAlarm> findJYAlarmByHql(String hql);

	public void updateJYAlarm(JYAlarm arg0);
	
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
	
	public List<?> findCostomizeObjHql(String hql);
}