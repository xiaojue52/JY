package com.station.service.impl;

import java.util.List;

import com.station.dao.JYCabinetDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYCabinet;
import com.station.service.JYMonitorService;

public class JYMonitorServiceImpl implements JYMonitorService {

	private JYCabinetDAO cabinetDAO;

	public void setCabinetDAO(JYCabinetDAO cabinetDAO) {
		this.cabinetDAO = cabinetDAO;
	}

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//final String hql="from Device device";
		int TotalCount=cabinetDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYCabinet> list=cabinetDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.getTotalCount(hql);
	}
}
