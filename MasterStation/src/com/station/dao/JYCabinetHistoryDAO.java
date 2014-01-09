package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYCabinetHistory;


public interface JYCabinetHistoryDAO {
	public void saveJYCabinetHistory(JYCabinetHistory arg0);

	public void removeJYCabinetHistory(JYCabinetHistory arg0);

	public JYCabinetHistory findJYCabinetHistoryById(String id);

	public List<JYCabinetHistory> findJYCabinetHistoryByHql(String hql);

	public void updateJYCabinetHistory(JYCabinetHistory arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYCabinetHistory> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}