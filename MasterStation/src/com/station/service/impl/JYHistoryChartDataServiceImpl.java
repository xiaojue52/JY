package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYHistoryChartDataDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYHistoryChartData;
import com.station.service.JYHistoryChartDataService;

public class JYHistoryChartDataServiceImpl implements JYHistoryChartDataService {

	private JYHistoryChartDataDAO historyChartDataDAO;

	public void setHistoryChartDataDAO(JYHistoryChartDataDAO historyChartDataDAO) {
		this.historyChartDataDAO = historyChartDataDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		int TotalCount=historyChartDataDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYHistoryChartData> list= historyChartDataDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
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
	public List<JYHistoryChartData> findJYHistoryChartDataByHql(String hql) {
		// TODO Auto-generated method stub
		return historyChartDataDAO.findJYHistoryChartDataByHql(hql);
	}

	@Override
	public JYHistoryChartData findJYHistoryChartDataById(String id) {
		// TODO Auto-generated method stub
		return historyChartDataDAO.findJYHistoryChartDataById(id);
	}

	@Override
	public void removeJYHistoryChartData(JYHistoryChartData arg0) {
		// TODO Auto-generated method stub
		//historyChartDataDAO.removeJYHistoryChartData(arg0);
		historyChartDataDAO.updateJYHistoryChartData(arg0);
	}

	@Override
	public void saveJYHistoryChartData(JYHistoryChartData arg0) {
		// TODO Auto-generated method stub
		historyChartDataDAO.saveJYHistoryChartData(arg0);
	}

	@Override
	public void updateJYHistoryChartData(JYHistoryChartData arg0) {
		// TODO Auto-generated method stub
		historyChartDataDAO.updateJYHistoryChartData(arg0);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return historyChartDataDAO.getTotalCount(hql,parameters);
	}
}
