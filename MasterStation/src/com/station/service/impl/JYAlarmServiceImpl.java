package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYAlarmDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYAlarm;
import com.station.service.JYAlarmService;

public class JYAlarmServiceImpl implements JYAlarmService {

	private JYAlarmDAO alarmDAO;

	public void setAlarmDAO(JYAlarmDAO alarmDAO) {
		this.alarmDAO = alarmDAO;
	}

	@Override
	public List<JYAlarm> findJYAlarmByHql(String hql) {
		// TODO Auto-generated method stub
		return alarmDAO.findJYAlarmByHql(hql);
	}

	@Override
	public JYAlarm findJYAlarmById(String id) {
		// TODO Auto-generated method stub
		return alarmDAO.findJYAlarmById(id);
	}

	@Override
	public void removeJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		alarmDAO.removeJYAlarm(arg0);
	}

	@Override
	public void saveJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		alarmDAO.saveJYAlarm(arg0);
	}

	@Override
	public void updateJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		alarmDAO.updateJYAlarm(arg0);
	}

	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		int TotalCount=alarmDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYAlarm> list= alarmDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
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
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return alarmDAO.getTotalCount(hql,parameters);
	}

	@Override
	public List<?> findCostomizeObjHql(String hql) {
		// TODO Auto-generated method stub
		return this.alarmDAO.findCostomizeObjHql(hql);
	}
}
