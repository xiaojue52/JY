package com.station.service.impl;

import java.util.List;

import com.station.dao.UnhandledExceptionDAO;
import com.station.pagebean.PageBean;
import com.station.po.UnhandledException;
import com.station.service.UnhandledExceptionService;

public class UnhandleExceptionServiceImpl implements UnhandledExceptionService {

	private UnhandledExceptionDAO unhandledExceptionDAO;

	public void setUnhandledExceptionDAO(UnhandledExceptionDAO unhandledExceptionDAO) {
		this.unhandledExceptionDAO = unhandledExceptionDAO;
	}

	@Override
	public List<UnhandledException> findAllDevice() {
		// TODO Auto-generated method stub
		return unhandledExceptionDAO.findAllDevice();
	}

	@Override
	public UnhandledException findDeviceById(Integer id) {
		// TODO Auto-generated method stub
		return unhandledExceptionDAO.findDeviceById(id);
	}

	@Override
	public void removeDevice(UnhandledException data) {
		// TODO Auto-generated method stub
		unhandledExceptionDAO.removeDevice(data);
	}

	@Override
	public void saveDevice(UnhandledException data) {
		// TODO Auto-generated method stub
		unhandledExceptionDAO.saveDevice(data);
	}

	@Override
	public void updateDevice(UnhandledException data) {
		// TODO Auto-generated method stub
		unhandledExceptionDAO.updateDevice(data);
	}

	@Override
	public List<UnhandledException> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		return unhandledExceptionDAO.findDevicesByOwner(owner);
	}

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//final String hql="from Device device";
		int TotalCount=unhandledExceptionDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<UnhandledException> list=unhandledExceptionDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
		return unhandledExceptionDAO.getTotalCount(hql);
	}

}
