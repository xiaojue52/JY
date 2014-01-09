package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYHistoryMonthChartData;


public interface JYHistoryMonthChartDataService {
	public void saveJYHistoryMonthChartData(JYHistoryMonthChartData arg0);

	public void removeJYHistoryMonthChartData(JYHistoryMonthChartData arg0);

	public JYHistoryMonthChartData findJYHistoryMonthChartDataById(String id);

	public List<JYHistoryMonthChartData> findJYHistoryMonthChartDataByHql(String hql);

	public void updateJYHistoryMonthChartData(JYHistoryMonthChartData arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
}
