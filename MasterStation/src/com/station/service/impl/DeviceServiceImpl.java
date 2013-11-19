package com.station.service.impl;

import java.util.List;

import com.station.dao.DeviceDAO;
import com.station.pagebean.PageBean;
import com.station.po.Device;
import com.station.service.DeviceService;

public class DeviceServiceImpl implements DeviceService {

	private DeviceDAO deviceDAO;

	public void setDeviceDAO(DeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	@Override
	public List<Device> findAllDevice() {
		// TODO Auto-generated method stub
		return deviceDAO.findAllDevice();
	}

	@Override
	public Device findDeviceById(Integer id) {
		// TODO Auto-generated method stub
		return deviceDAO.findDeviceById(id);
	}

	@Override
	public List<Device> findDevicesByBox(String box) {
		// TODO Auto-generated method stub
		return deviceDAO.findDevicesByBox(box);
	}

	@Override
	public void removeDevice(Device data) {
		// TODO Auto-generated method stub
		deviceDAO.removeDevice(data);
	}

	@Override
	public void saveDevice(Device data) {
		// TODO Auto-generated method stub
		deviceDAO.saveDevice(data);
	}

	@Override
	public void updateDevice(Device data) {
		// TODO Auto-generated method stub
		deviceDAO.updateDevice(data);
	}

	/*@Override
	public List<Device> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		return deviceDAO.findDevicesByOwner(owner);
	}*/

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		//final String hql="from Device device";
		int TotalCount=deviceDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<Device> list=deviceDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
		return deviceDAO.getTotalCount(hql);
	}

	@Override
	public Device findDeviceByIdentify(String identify) {
		// TODO Auto-generated method stub
		return deviceDAO.findDeviceByIdentify(identify);
	}

}
