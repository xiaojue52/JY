package com.station.service;

public interface JYTimerTaskService {
	/**
	 * 每整点半小时读取当前设备的温度（当前设备最新的温度记录），存入数据库
	 */
	public void saveHistoryChartData();
	/**
	 * 每天执行一次读取当天温度的最大值和最小值存入数据库
	 */
	public void saveHistoryMonthChartData();
	/**
	 * 隔一定的时间监测设备温度是否变化异常
	 */
	public void saveCheckedTempAlarm();
	/**
	 * 清除3个月之前的异常数据
	 */
	public void removeCabinetStatusAlarmAtFixedRate();
}

