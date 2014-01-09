package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYHistoryMonthChartDataDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYHistoryMonthChartData;
import com.station.service.JYHistoryMonthChartDataService;

public class JYHistoryMonthChartDataServiceImpl implements JYHistoryMonthChartDataService {

	private JYHistoryMonthChartDataDAO historyMonthChartDataDAO;

	public void setHistoryMonthChartDataDAO(JYHistoryMonthChartDataDAO historyMonthChartDataDAO) {
		this.historyMonthChartDataDAO = historyMonthChartDataDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		int TotalCount=historyMonthChartDataDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYHistoryMonthChartData> list= historyMonthChartDataDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
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
	public List<JYHistoryMonthChartData> findJYHistoryMonthChartDataByHql(String hql) {
		// TODO Auto-generated method stub
		return historyMonthChartDataDAO.findJYHistoryMonthChartDataByHql(hql);
	}

	@Override
	public JYHistoryMonthChartData findJYHistoryMonthChartDataById(String id) {
		// TODO Auto-generated method stub
		return historyMonthChartDataDAO.findJYHistoryMonthChartDataById(id);
	}

	@Override
	public void removeJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
		// TODO Auto-generated method stub
		//historyMonthChartDataDAO.removeJYHistoryMonthChartData(arg0);
		historyMonthChartDataDAO.updateJYHistoryMonthChartData(arg0);
	}

	@Override
	public void saveJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
		// TODO Auto-generated method stub
		historyMonthChartDataDAO.saveJYHistoryMonthChartData(arg0);
	}

	@Override
	public void updateJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
		// TODO Auto-generated method stub
		historyMonthChartDataDAO.updateJYHistoryMonthChartData(arg0);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return historyMonthChartDataDAO.getTotalCount(hql,parameters);
	}
}
