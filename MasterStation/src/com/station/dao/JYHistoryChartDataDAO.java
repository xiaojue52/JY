package com.station.dao;

import java.util.List;

import com.station.po.JYHistoryChartData;


public interface JYHistoryChartDataDAO {
	public void saveJYHistoryChartData(JYHistoryChartData arg0);

	public void removeJYHistoryChartData(JYHistoryChartData arg0);

	public JYHistoryChartData findJYHistoryChartDataById(String id);

	public List<JYHistoryChartData> findJYHistoryChartDataByHql(String hql);

	public void updateJYHistoryChartData(JYHistoryChartData arg0);
	
	public int getTotalCount(String hql);

	public List<JYHistoryChartData> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}