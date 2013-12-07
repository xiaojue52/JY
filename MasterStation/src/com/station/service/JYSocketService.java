package com.station.service;

import java.util.List;
import java.util.Map;


public interface JYSocketService {
	public void saveDate(String cabNumber,Map<Integer,List<Float>> map,String createDate);
}