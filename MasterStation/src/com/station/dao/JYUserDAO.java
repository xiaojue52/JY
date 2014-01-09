package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYUser;


public interface JYUserDAO {
	public void saveUser(JYUser user);

	public void removeUser(JYUser user);

	public JYUser findUserById(String id);

	public List<JYUser> findUserByHql(String hql);

	public void updateUser(JYUser user);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYUser> getPerPage(final String hql,final int startRow,final int countPerpage, Map<String,Object> parameters);
	
}