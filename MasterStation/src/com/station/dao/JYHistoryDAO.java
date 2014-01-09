package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYHistory;


public interface JYHistoryDAO {
	public void saveJYHistory(JYHistory arg0);

	public void removeJYHistory(JYHistory arg0);

	public JYHistory findJYHistoryById(String id);

	public List<JYHistory> findJYHistoryByHql(String hql);

	public void updateJYHistory(JYHistory arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYHistory> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}