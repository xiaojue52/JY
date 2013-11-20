package com.station.dao;

import java.util.List;

import com.station.po.JYConstant;


public interface JYConstantDAO {
	public void saveJYConstant(JYConstant arg0);

	public void removeJYConstant(JYConstant arg0);

	public JYConstant findJYConstantById(Integer id);

	public List<JYConstant> findJYConstantByHql(String hql);

	public void updateJYConstant(JYConstant arg0);
	
}