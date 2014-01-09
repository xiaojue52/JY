package com.station.service;

import java.util.List;
import java.util.Map;

import com.station.pagebean.PageBean;
import com.station.po.JYUser;


public interface JYUserService {
	public void saveUser(JYUser user);

	public int removeUser(JYUser user);

	public JYUser findUserById(String id);

	public List<JYUser> findUserByHql(String hql);

	public void updateUser(JYUser user);
    
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public  PageBean getPerPage(int countPerpage,int currentPage, String hql,Map<String,Object> parameters);
}
