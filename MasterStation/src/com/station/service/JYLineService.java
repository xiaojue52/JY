package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYLine;


public interface JYLineService {
	public void saveLine(JYLine arg0);

	public void removeLine(JYLine arg0);

	public JYLine findLineById(String id);

	public List<JYLine> findAllLineByHql(String hql);

	public void updateLine(JYLine arg0);
    
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
}
