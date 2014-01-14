package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYUserGroup;


public interface JYUserGroupService {
	public void saveJYUserGroup(JYUserGroup arg0);

	public int removeJYUserGroup(JYUserGroup arg0);

	public JYUserGroup findJYUserGroupById(Integer id);

	public List<JYUserGroup> findJYUserGroupByHql(String hql,Map<String,Object> parameters);

	public void updateJYUserGroup(JYUserGroup arg0);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public  PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
}