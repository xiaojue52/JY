package com.station.service.impl;

import java.util.List;
import java.util.Map;

import com.station.dao.JYLineDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYCabinet;
import com.station.po.JYLine;
import com.station.service.JYCabinetService;
import com.station.service.JYLineService;

public class JYLineServiceImpl implements JYLineService {

	private JYLineDAO lineDAO;
	private JYCabinetService cabinetService;


	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}

	public void setLineDAO(JYLineDAO lineDAO) {
		this.lineDAO = lineDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=lineDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYLine> list= lineDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		
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
	public List<JYLine> findAllLineByHql(String hql) {
		// TODO Auto-generated method stub
		return lineDAO.findAllLineByHql(hql);
	}

	@Override
	public JYLine findLineById(String id) {
		// TODO Auto-generated method stub
		return lineDAO.findLineById(id);
	}

	@Override
	public void removeLine(JYLine arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(0);
		lineDAO.updateLine(arg0);
		String hql = "from JYCabinet cabinet where cabinet.line.lineId = '"+arg0.getLineId()+"'";
		List<JYCabinet> list = cabinetService.findJYCabinetByHql(hql);
		this.cabinetService.removeJYCabinets(list);
	}

	@Override
	public void saveLine(JYLine arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(1);
		lineDAO.saveLine(arg0);
	}

	@Override
	public void updateLine(JYLine arg0) {
		// TODO Auto-generated method stub
		arg0.setTag(1);
		
		lineDAO.updateLine(arg0);
	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return lineDAO.getTotalCount(hql,parameters);
	}
}
