package com.station.dao;

import java.util.List;

import com.station.po.JYDetector;


public interface JYDetectorDAO {
	public void saveJYDetector(JYDetector arg0);

	public void removeJYDetector(JYDetector arg0);

	public JYDetector findJYDetectorById(String id);

	public List<JYDetector> findJYDetectorByHql(String hql);

	public void updateJYDetector(JYDetector arg0);
	
	public int getTotalCount(String hql);

	public List<JYDetector> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}