package com.station.service.impl;

import java.util.List;

import com.station.dao.DeviceHistoryDAO;
import com.station.pagebean.PageBean;
import com.station.po.DeviceHistory;
import com.station.service.DeviceHistoryService;

public class DeviceHistoryServiceImpl implements DeviceHistoryService {

	private DeviceHistoryDAO deviceHistoryDAO;


	public void setDeviceHistoryDAO(DeviceHistoryDAO deviceHistoryDAO) {
		this.deviceHistoryDAO = deviceHistoryDAO;
	}

	@Override
	public List<DeviceHistory> findAllDevice() {
		// TODO Auto-generated method stub
		return deviceHistoryDAO.findAllDevice();
	}

	@Override
	public DeviceHistory findDeviceById(Integer id) {
		// TODO Auto-generated method stub
		return deviceHistoryDAO.findDeviceById(id);
	}

	@Override
	public List<DeviceHistory> findDevicesByLine(String line, String owner) {
		// TODO Auto-generated method stub
		return deviceHistoryDAO.findDevicesByLine(line,owner);
	}

	@Override
	public void removeDevice(DeviceHistory data) {
		// TODO Auto-generated method stub
		deviceHistoryDAO.removeDevice(data);
	}

	@Override
	public void saveDevice(DeviceHistory data) {
		// TODO Auto-generated method stub
		deviceHistoryDAO.saveDevice(data);
	}

	@Override
	public void updateDevice(DeviceHistory data) {
		// TODO Auto-generated method stub
		deviceHistoryDAO.updateDevice(data);
	}

	@Override
	public List<DeviceHistory> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		return deviceHistoryDAO.findDevicesByOwner(owner);
	}

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//final String hql="from Device device";
		int TotalCount=deviceHistoryDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<DeviceHistory> list=deviceHistoryDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
		return deviceHistoryDAO.getTotalCount(hql);
	}

}
