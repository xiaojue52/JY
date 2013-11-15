package com.station.dao;

import java.util.List;

import com.station.po.JYCabinet;


public interface JYCabinetDAO {
	public void saveJYCabinet(JYCabinet arg0);

	public void removeJYCabinet(JYCabinet arg0);

	public JYCabinet findJYCabinetById(String id);

	public List<JYCabinet> findJYCabinetByHql(String hql);

	public void updateJYCabinet(JYCabinet arg0);
	
	public int getTotalCount(String hql);

	public List<JYCabinet> getPerPage(final String hql,final int startRow,final int countPerpage);
	
}