package com.station.dao;

import java.util.List;

import com.station.po.JYAlarm;


public interface JYAlarmDAO {
	/**
	 * 保存记录
	 * @param arg0
	 */
	public void saveJYAlarm(JYAlarm arg0);

	/**
	 * 删除记录
	 * @param arg0
	 */
	public void removeJYAlarm(JYAlarm arg0);

	/**
	 * 查找记录
	 * @param id
	 * @return
	 */
	public JYAlarm findJYAlarmById(String id);

	/**
	 * 查找符合条件的记录
	 * @param hql
	 * @return
	 */
	public List<JYAlarm> findJYAlarmByHql(String hql);

	/**
	 * 更新记录
	 * @param arg0
	 */
	public void updateJYAlarm(JYAlarm arg0);
	
	/**
	 * 获取记录总数
	 * @param hql
	 * @return
	 */
	public int getTotalCount(String hql);

	/**
	 * 获取分页记录
	 * @param hql
	 * @param startRow
	 * @param countPerpage
	 * @return
	 */
	public List<JYAlarm> getPerPage(final String hql,final int startRow,final int countPerpage);
	
	/**
	 * 删除符合条件的记录，
	 * 注：此处参数为sql语法，并非hql语法
	 * @param sql
	 */
	public void removeMultiplAlarms(String sql);
	
	/**
	 * 取出自定义的数据量
	 * 注：此处参数为hql语法
	 * @param hql
	 * @return
	 */
	public List<?> findCostomizeObjHql(String hql);
	
}