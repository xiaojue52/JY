package com.station.service;

import java.util.List;

import com.station.po.JYConstant;


public interface JYConstantService {
	public void saveJYConstant(JYConstant arg0);

	public int removeJYConstant(JYConstant arg0);

	public JYConstant findJYConstantById(Integer id);

	public List<JYConstant> findJYConstantByHql(String hql);

	public void updateJYConstant(JYConstant arg0);
	
}