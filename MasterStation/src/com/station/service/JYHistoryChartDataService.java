package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYHistoryChartData;


public interface JYHistoryChartDataService {
	public void saveJYHistoryChartData(JYHistoryChartData arg0);

	public void removeJYHistoryChartData(JYHistoryChartData arg0);

	public JYHistoryChartData findJYHistoryChartDataById(String id);

	public List<JYHistoryChartData> findJYHistoryChartDataByHql(String hql);

	public void updateJYHistoryChartData(JYHistoryChartData arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
}
