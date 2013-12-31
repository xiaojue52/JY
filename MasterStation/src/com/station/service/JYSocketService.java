package com.station.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.station.po.JYCabinet;


public interface JYSocketService {
	public void saveDate(String cabId,Map<Integer,List<Float>> map,String createDate);
	//public void saveAlarmDate(String cabNumber,Map<Integer,List<Float>> map,String createDate);
	public void updateCabinetStatus(String cabId);
	public void saveAlarm(String cabId,int type,Date date,String content);
	public JYCabinet getCabinet(String cabId);//分钟
	public boolean cabinetIsExist(String cabId);
	public List<JYCabinet> findCabinetsByHql(String hql);
	//public JYCabinet getCabinetById(String id);
}