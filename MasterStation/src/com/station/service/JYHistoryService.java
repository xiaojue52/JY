package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYHistory;


public interface JYHistoryService {
	public void saveJYHistory(JYHistory arg0);

	public void removeJYHistory(JYHistory arg0);

	public JYHistory findJYHistoryById(String id);

	public List<JYHistory> findJYHistoryByHql(String hql);

	public void updateJYHistory(JYHistory arg0);
    
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
}
