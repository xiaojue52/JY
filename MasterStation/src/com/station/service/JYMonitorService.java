package com.station.service;

import com.station.pagebean.PageBean;


public interface JYMonitorService {
	
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
}
