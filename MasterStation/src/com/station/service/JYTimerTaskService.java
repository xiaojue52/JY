package com.station.service;

public interface JYTimerTaskService {
	public void saveHistoryChartData();
	public void saveHistoryMonthChartData();
	public void saveCheckedTempAlarm();
	public void removeCabinetStatusAlarmAtFixedRate();
}

