package com.station.service.impl;

import java.util.List;

import com.station.dao.JYCabinetDAO;
import com.station.dao.JYUserGroupDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYUserGroup;
import com.station.service.JYUserGroupService;

public class JYUserGroupServiceImpl implements JYUserGroupService {

	private JYUserGroupDAO userGroupDAO;
	private JYCabinetDAO cabinetDAO;
	
	public void setCabinetDAO(JYCabinetDAO cabinetDAO) {
		this.cabinetDAO = cabinetDAO;
	}

	public void setUserGroupDAO(JYUserGroupDAO userGroupDAO) {
		this.userGroupDAO = userGroupDAO;
	}



	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYJYUserGroup arg0 where arg0_level = 'arg0' or arg0_level = 'com_admin' order by id desc";
		int TotalCount=userGroupDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYUserGroup> list=userGroupDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
	public List<JYUserGroup> findJYUserGroupByHql(String hql) {
		// TODO Auto-generated method stub
		return userGroupDAO.findJYUserGroupByHql(hql);
	}

	@Override
	public JYUserGroup findJYUserGroupById(Integer id) {
		// TODO Auto-generated method stub
		return userGroupDAO.findJYUserGroupById(id);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return userGroupDAO.getTotalCount(hql);
	}

	@Override
	public int removeJYUserGroup(JYUserGroup arg0) {
		// TODO Auto-generated method stub
		String hql = "from JYCabinet cabinet where cabinet.user.userGroup.id = '"+arg0.getId()+"'";
		if(this.cabinetDAO.findJYCabinetByHql(hql).size()>0){
			return -1;
		}
		else{
			userGroupDAO.removeJYUserGroup(arg0);
			return 1;
		}
		
	}

	@Override
	public void saveJYUserGroup(JYUserGroup arg0) {
		// TODO Auto-generated method stub
		userGroupDAO.saveJYUserGroup(arg0);
	}

	@Override
	public void updateJYUserGroup(JYUserGroup arg0) {
		// TODO Auto-generated method stub
		userGroupDAO.updateJYUserGroup(arg0);
	}

}
