package com.station.service.impl;

import java.util.List;

import com.station.dao.JYCabinetDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYCabinet;
import com.station.service.JYCabinetService;

public class JYCabinetServiceImpl implements JYCabinetService {

	private JYCabinetDAO cabinetDAO;

	public void setCabinetDAO(JYCabinetDAO cabinetDAO) {
		this.cabinetDAO = cabinetDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=cabinetDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYCabinet> list= cabinetDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
	public List<JYCabinet> findJYCabinetByHql(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.findJYCabinetByHql(hql);
	}

	@Override
	public JYCabinet findJYCabinetById(String id) {
		// TODO Auto-generated method stub
		return cabinetDAO.findJYCabinetById(id);
	}

	@Override
	public void removeJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		cabinetDAO.removeJYCabinet(arg0);
	}

	@Override
	public void saveJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		cabinetDAO.saveJYCabinet(arg0);
	}

	@Override
	public void updateJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		cabinetDAO.updateJYCabinet(arg0);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.getTotalCount(hql);
	}
}
