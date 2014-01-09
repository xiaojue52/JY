package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYCabinetHistory;


public interface JYCabinetHistoryService {
	public void saveJYCabinetHistory(JYCabinetHistory arg0);

	public void removeJYCabinetHistory(JYCabinetHistory arg0);

	public JYCabinetHistory findJYCabinetHistoryById(String id);

	public List<JYCabinetHistory> findJYCabinetHistoryByHql(String hql);

	public void updateJYCabinetHistory(JYCabinetHistory arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
	
	public void removeJYCabinetHistorys(List<JYCabinetHistory> list);
}
