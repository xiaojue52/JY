package com.station.service.impl;

import java.util.List;

import com.station.dao.JYDetectorDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYDetector;
import com.station.service.JYDetectorService;

public class JYDetectorServiceImpl implements JYDetectorService {

	private JYDetectorDAO detectorDAO;

	public void setDetectorDAO(JYDetectorDAO detectorDAO) {
		this.detectorDAO = detectorDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=detectorDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYDetector> list= detectorDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
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
	public List<JYDetector> findJYDetectorByHql(String hql) {
		// TODO Auto-generated method stub
		return detectorDAO.findJYDetectorByHql(hql);
	}

	@Override
	public JYDetector findJYDetectorById(String id) {
		// TODO Auto-generated method stub
		return detectorDAO.findJYDetectorById(id);
	}

	@Override
	public void removeJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(0);
		detectorDAO.updateJYDetector(arg0);
	}
	@Override
	public void removeJYDetectors(List<JYDetector> list){
		for (int i=0;i<list.size();i++){
			this.removeJYDetector(list.get(i));
		}
	}
	@Override
	public void saveJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		detectorDAO.saveJYDetector(arg0);
	}

	@Override
	public void updateJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(1);
		detectorDAO.updateJYDetector(arg0);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return detectorDAO.getTotalCount(hql);
	}
}
