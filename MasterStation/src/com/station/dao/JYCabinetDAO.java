package com.station.dao;

import java.util.List;
import java.util.Map;

import com.station.po.JYCabinet;


public interface JYCabinetDAO {
	/**
	 * 保存记录，返回记录id
	 * @param arg0
	 * @return
	 */
	public String saveJYCabinet(JYCabinet arg0);

	public void removeJYCabinet(JYCabinet arg0);

	public JYCabinet findJYCabinetById(String id);

	public List<JYCabinet> findJYCabinetByHql(String hql);

	public void updateJYCabinet(JYCabinet arg0);
	
	public int getTotalCount(String hql,Map<String,Object> parameters);

	public List<JYCabinet> getPerPage(final String hql,final int startRow,final int countPerpage,Map<String,Object> parameters);
	
}