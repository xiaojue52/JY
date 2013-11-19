package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYDetector;


public interface JYDetectorService {
	public void saveJYDetector(JYDetector arg0);

	public void removeJYDetector(JYDetector arg0);

	public JYDetector findJYDetectorById(String id);

	public List<JYDetector> findJYDetectorByHql(String hql);

	public void updateJYDetector(JYDetector arg0);
    
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
}
