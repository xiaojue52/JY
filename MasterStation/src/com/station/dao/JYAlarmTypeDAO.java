package com.station.dao;

import java.util.List;

import com.station.po.JYAlarmType;


public interface JYAlarmTypeDAO {
	/**
	 * 保存记录
	 * @param arg0
	 */
	public void saveJYAlarmType(JYAlarmType arg0);

	/**
	 * 删除记录
	 * @param arg0
	 */
	public void removeJYAlarmType(JYAlarmType arg0);

	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public JYAlarmType findJYAlarmTypeById(String id);

	/**
	 * 按条件查找，返回list
	 * @param hql
	 * @return
	 */
	public List<JYAlarmType> findJYAlarmTypeByHql(String hql);

	/**
	 * 更新记录
	 * @param arg0
	 */
	public void updateJYAlarmType(JYAlarmType arg0);
	
}