package com.station.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.station.po.JYCabinet;


public interface JYSocketService {
	/**
	 * 保存采集数据，并判断温度是否异常
	 * @param cabId
	 * @param map Integer: 间隔序号 List: 监测端温度
	 * @param createDate 时间字符串
	 */
	public void saveData(String cabId,Map<Integer,List<Float>> map,String createDate);
	//public void saveAlarmDate(String cabNumber,Map<Integer,List<Float>> map,String createDate);
	/**
	 * 清除报警，设备状态改为在线
	 * @param cabId 柜体id
	 */
	public void updateCabinetStatus(String cabId);
	/**
	 * 保存异常
	 * @param cabId 柜体id
	 * @param type 报警类型
	 * @param date 报警日期
	 * @param content 报警内容
	 */
	public void saveAlarm(String cabId,int type,Date date,String content,String condition);
	/**
	 * 获取柜体
	 * @param cabId 柜体id
	 * @return 返回柜体实例
	 */
	public JYCabinet getCabinet(String cabId);
	/**
	 * 检测前端是否存在柜体
	 * @param cabId 柜体id
	 * @return ture：存在  false：不存在
	 */
	public boolean cabinetIsExist(String cabId);
	/**
	 * 按照hql查询条件查找柜体，返回list
	 * @param hql
	 * @return
	 */
	public List<JYCabinet> findCabinetsByHql(String hql);
	/**
	 * 得到最新采集时间，用来判断设备是否离线
	 * @param cabId
	 * @return
	 */
	public String getHistoryDateString(String cabId);
}