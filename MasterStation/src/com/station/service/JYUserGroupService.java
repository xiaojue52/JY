package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYUserGroup;


public interface JYUserGroupService {
	public void saveJYUserGroup(JYUserGroup arg0);

	public int removeJYUserGroup(JYUserGroup arg0);

	public JYUserGroup findJYUserGroupById(Integer id);

	public List<JYUserGroup> findJYUserGroupByHql(String hql);

	public void updateJYUserGroup(JYUserGroup arg0);
    
	public int getTotalCount(String hql);

	public  PageBean getPerPage(int countPerpage,int currentPage, String hql);
}