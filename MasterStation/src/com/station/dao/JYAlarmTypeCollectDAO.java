package com.station.dao;

import java.util.List;

import com.station.po.JYAlarmTypeCollect;


public interface JYAlarmTypeCollectDAO {
	/**
	 * 保存记录
	 * @param arg0
	 */
	public void saveJYAlarmTypeCollect(JYAlarmTypeCollect arg0);

	/**
	 * 删除记录
	 * @param arg0
	 */
	public void removeJYAlarmTypeCollect(JYAlarmTypeCollect arg0);

	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public JYAlarmTypeCollect findJYAlarmTypeCollectById(String id);

	/**
	 * 根据hql语句查找记录，返回list
	 * @param hql
	 * @return
	 */
	public List<JYAlarmTypeCollect> findJYAlarmTypeCollectByHql(String hql);

	/**
	 * 更新记录
	 * @param arg0
	 */
	public void updateJYAlarmTypeCollect(JYAlarmTypeCollect arg0);
	
}