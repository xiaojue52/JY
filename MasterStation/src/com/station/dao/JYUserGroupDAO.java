package com.station.dao;

import java.util.List;
import com.station.po.JYUserGroup;


public interface JYUserGroupDAO {
	public void saveJYUserGroup(JYUserGroup arg0);

	public void removeJYUserGroup(JYUserGroup arg0);

	public JYUserGroup findJYUserGroupById(Integer id);

	public List<JYUserGroup> findJYUserGroupByHql(String hql);

	public void updateJYUserGroup(JYUserGroup arg0);
	
	public int getTotalCount(String hql);

	public List<JYUserGroup> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}