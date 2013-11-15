package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYUser;


public interface JYUserService {
	public void saveUser(JYUser user);

	public void removeUser(JYUser user);

	public JYUser findUserById(String id);

	public List<JYUser> findUserByHql(String hql);

	public void updateUser(JYUser user);
    
	public int getTotalCount(String hql);

	public  PageBean getPerPage(int countPerpage,int currentPage, String hql);
}
