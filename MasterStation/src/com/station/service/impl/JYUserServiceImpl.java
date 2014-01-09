package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYUserDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYUser;
import com.station.service.JYUserService;

public class JYUserServiceImpl implements JYUserService {

	private JYUserDAO userDAO;

	public void setUserDAO(JYUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=userDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYUser> list=userDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
		PageBean pageBean=new PageBean();
		pageBean.setCurrentPage(CurrentPage);
		pageBean.setCountPerpage(CountPerpage);
		pageBean.setTotalCount(TotalCount);
		pageBean.setTotalPage(TotalPage);
		pageBean.setList(list);
		pageBean.init();		
		
		return pageBean;
	}

	@Override
	public List<JYUser> findUserByHql(String hql) {
		// TODO Auto-generated method stub
		return userDAO.findUserByHql(hql);
	}

	@Override
	public JYUser findUserById(String id) {
		// TODO Auto-generated method stub
		return userDAO.findUserById(id);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return userDAO.getTotalCount(hql,parameters);
	}

	@Override
	public int removeUser(JYUser user) {
		// TODO Auto-generated method stub
		userDAO.removeUser(user);
		return 1;
	}

	@Override
	public void saveUser(JYUser user) {
		// TODO Auto-generated method stub
		userDAO.saveUser(user);
	}

	@Override
	public void updateUser(JYUser user) {
		// TODO Auto-generated method stub
		userDAO.updateUser(user);
	}

}
