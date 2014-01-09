package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYHistoryMonthChartData;


public interface JYHistoryMonthChartDataDAO {
	public void saveJYHistoryMonthChartData(JYHistoryMonthChartData arg0);

	public void removeJYHistoryMonthChartData(JYHistoryMonthChartData arg0);

	public JYHistoryMonthChartData findJYHistoryMonthChartDataById(String id);

	public List<JYHistoryMonthChartData> findJYHistoryMonthChartDataByHql(String hql);

	public void updateJYHistoryMonthChartData(JYHistoryMonthChartData arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYHistoryMonthChartData> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}