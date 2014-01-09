package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYHistoryDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYHistory;
import com.station.service.JYHistoryService;

public class JYHistoryServiceImpl implements JYHistoryService {

	private JYHistoryDAO historyDAO;

	public void setHistoryDAO(JYHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		int TotalCount=historyDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYHistory> list= historyDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
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
	public List<JYHistory> findJYHistoryByHql(String hql) {
		// TODO Auto-generated method stub
		return historyDAO.findJYHistoryByHql(hql);
	}

	@Override
	public JYHistory findJYHistoryById(String id) {
		// TODO Auto-generated method stub
		return historyDAO.findJYHistoryById(id);
	}

	@Override
	public void removeJYHistory(JYHistory arg0) {
		// TODO Auto-generated method stub
		//historyDAO.removeJYHistory(arg0);
		historyDAO.updateJYHistory(arg0);
	}

	@Override
	public void saveJYHistory(JYHistory arg0) {
		// TODO Auto-generated method stub
		historyDAO.saveJYHistory(arg0);
	}

	@Override
	public void updateJYHistory(JYHistory arg0) {
		// TODO Auto-generated method stub
		historyDAO.updateJYHistory(arg0);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return historyDAO.getTotalCount(hql,parameters);
	}
}
