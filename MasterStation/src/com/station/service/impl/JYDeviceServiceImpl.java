package com.station.service.impl;

import java.util.List;

import com.station.dao.JYDeviceDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;

public class JYDeviceServiceImpl implements JYDeviceService {

	private JYDeviceDAO deviceDAO;
	private JYDetectorService detectorService;
	private JYDetector detector;


	public void setDetectorService(JYDetectorService detectorService) {
		this.detectorService = detectorService;
	}

	public void setDeviceDAO(JYDeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=deviceDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYDevice> list= deviceDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
	public List<JYDevice> findJYDeviceByHql(String hql) {
		// TODO Auto-generated method stub
		return deviceDAO.findJYDeviceByHql(hql);
	}

	@Override
	public JYDevice findJYDeviceById(String id) {
		// TODO Auto-generated method stub
		return deviceDAO.findJYDeviceById(id);
	}

	@Override
	public void removeJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(0);
		deviceDAO.updateJYDevice(arg0);
		String hql = "from JYDetector detector where detector.device.deviceId = '"+arg0.getDeviceId()+"'";
		List<JYDetector> list = detectorService.findJYDetectorByHql(hql);
		detectorService.removeJYDetectors(list);
	}
	@Override
	public void removeJYDevices(List<JYDevice> list){
		for (int i=0;i<list.size();i++){
			this.removeJYDevice(list.get(i));
		}
	}
	@Override
	public void saveJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(1);
		deviceDAO.saveJYDevice(arg0);
		for (int i=0;i<4;i++){
			detector = new JYDetector();
			detector.setDevice(deviceDAO.findJYDeviceById(arg0.getDeviceId()));
			switch(i){
				case 0:
					detector.setName("A相");
					break;
				case 1:
					detector.setName("B相");
					break;
				case 2:
					detector.setName("C相");
					break;
				case 3:
					detector.setName("环境");
					break;
				default:
					break;
			}
			detector.setTag(1);
			detector.setUnit("℃");
			detectorService.saveJYDetector(detector);
		}	
	}

	@Override
	public void updateJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(1);
		deviceDAO.updateJYDevice(arg0);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return deviceDAO.getTotalCount(hql);
	}
}
