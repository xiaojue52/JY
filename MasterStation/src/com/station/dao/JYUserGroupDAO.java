package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYUserGroup;


public interface JYUserGroupDAO {
	public void saveJYUserGroup(JYUserGroup arg0);

	public void removeJYUserGroup(JYUserGroup arg0);

	public JYUserGroup findJYUserGroupById(Integer id);

	public List<JYUserGroup> findJYUserGroupByHql(String hql,Map<String,Object> parameters);

	public void updateJYUserGroup(JYUserGroup arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYUserGroup> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}