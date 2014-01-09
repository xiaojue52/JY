package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYDetector;


public interface JYDetectorService {
	public void saveJYDetector(JYDetector arg0);

	public void removeJYDetector(JYDetector arg0);

	public JYDetector findJYDetectorById(String id);

	public List<JYDetector> findJYDetectorByHql(String hql);

	public void updateJYDetector(JYDetector arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
	
	public void removeJYDetectors(List<JYDetector> list);
}
