package com.station.service;

import java.util.Map;

import com.station.pagebean.PageBean;


public interface JYMonitorService {
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
}
