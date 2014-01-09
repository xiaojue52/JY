package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYAlarm;


public interface JYAlarmService {
	public void saveJYAlarm(JYAlarm arg0);

	public void removeJYAlarm(JYAlarm arg0);

	public JYAlarm findJYAlarmById(String id);

	public List<JYAlarm> findJYAlarmByHql(String hql);

	public void updateJYAlarm(JYAlarm arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
	/**
	 * 查找记录返回自定object[]
	 * @param hql 参数hql，非sql
	 * @return
	 */
	public List<?> findCostomizeObjHql(String hql);
}