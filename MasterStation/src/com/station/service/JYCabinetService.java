package com.station.service;

import java.util.List;

import com.station.pagebean.PageBean;
import com.station.po.JYCabinet;


public interface JYCabinetService {
	public void saveJYCabinet(JYCabinet arg0);

	public void removeJYCabinet(JYCabinet arg0);

	public JYCabinet findJYCabinetById(String id);

	public List<JYCabinet> findJYCabinetByHql(String hql);

	public void updateJYCabinet(JYCabinet arg0);
    
	public int getTotalCount(String hql);

	public PageBean getPerPage(int countPerpage,int currentPage, String hql);
	
	public void removeJYCabinets(List<JYCabinet> list);
	
	public boolean cabinetIsExist(String cabId);
}
