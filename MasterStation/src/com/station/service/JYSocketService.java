package com.station.service;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface JYSocketService {
	public void saveDate(String cabNumber,Map<Integer,List<Float>> map,String createDate);
	//public void saveAlarmDate(String cabNumber,Map<Integer,List<Float>> map,String createDate);
	public void updateCabinetStatus(String cabNumber);
	public void saveAlarm(String cabNumber,int type,Date date,String content);
}