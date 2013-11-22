package com.station.dao;

import java.util.List;

import com.station.po.JYLine;


public interface JYLineDAO {
	public void saveLine(JYLine arg0);

	public void removeLine(JYLine arg0);

	public JYLine findLineById(String id);

	public List<JYLine> findAllLineByHql(String hql);

	public void updateLine(JYLine arg0);
	
	public int getTotalCount(String hql);

	public List<JYLine> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}