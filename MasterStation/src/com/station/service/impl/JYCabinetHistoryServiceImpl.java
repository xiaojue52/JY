package com.station.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.station.dao.JYCabinetHistoryDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYCabinetHistory;
import com.station.po.JYDevice;
import com.station.po.JYHistory;
import com.station.service.JYCabinetHistoryService;
import com.station.service.JYHistoryService;

public class JYCabinetHistoryServiceImpl implements JYCabinetHistoryService {

	private JYCabinetHistoryDAO cabinetHistoryDAO;
	private JYHistoryService historyService;


	public void setHistoryService(JYHistoryService historyService) {
		this.historyService = historyService;
	}

	public void setCabinetHistoryDAO(JYCabinetHistoryDAO cabinetHistoryDAO) {
		this.cabinetHistoryDAO = cabinetHistoryDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=cabinetHistoryDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYCabinetHistory> list= cabinetHistoryDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		for (int i=0;i<list.size();i++){
			String historyHql = "from JYHistory history where history.cabinetHistory.id = '"+list.get(i).getId()+"'";
			List<JYHistory> historyList = historyService.findJYHistoryByHql(historyHql);
			Map<JYDevice, List<JYHistory>> map = new HashMap<JYDevice, List<JYHistory>>();
			while (historyList.size()>0){
				List<JYHistory> list1 = new ArrayList<JYHistory>();
				JYDevice device = historyList.get(0).getDetector().getDevice();
				JYHistory history = historyList.get(0);
				list1.add(history);
				historyList.remove(0);
				int j=0;
				while (historyList.size()!=j){
					
					if (history.getDetector().getDevice().equals(historyList.get(j).getDetector().getDevice())){
						list1.add(historyList.get(j));
						historyList.remove(j);
						j --;
					}
					j ++;
				}
				map.put(device, list1);
			}
			list.get(i).setMap(map);
		}
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
	public List<JYCabinetHistory> findJYCabinetHistoryByHql(String hql) {
		// TODO Auto-generated method stub
		return cabinetHistoryDAO.findJYCabinetHistoryByHql(hql);
	}

	@Override
	public JYCabinetHistory findJYCabinetHistoryById(String id) {
		// TODO Auto-generated method stub
		return cabinetHistoryDAO.findJYCabinetHistoryById(id);
	}

	@Override
	public void removeJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeJYCabinetHistorys(List<JYCabinetHistory> list){
		for (int i=0;i<list.size();i++){
			this.removeJYCabinetHistory(list.get(i));
		}
	}
	
	@Override
	public void saveJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		cabinetHistoryDAO.saveJYCabinetHistory(arg0);
	}

	@Override
	public void updateJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		cabinetHistoryDAO.updateJYCabinetHistory(arg0);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return cabinetHistoryDAO.getTotalCount(hql);
	}
}
