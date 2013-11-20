package com.station.service;

import java.util.List;

import com.station.po.JYKeyGenerator;


public interface JYKeyGeneratorService {
	public void saveJYKeyGenerator(JYKeyGenerator arg0);

	public void removeJYKeyGenerator(JYKeyGenerator arg0);

	public JYKeyGenerator findJYKeyGeneratorById(String id);

	public List<JYKeyGenerator> findJYKeyGeneratorByHql(String hql);

	public void updateJYKeyGenerator(JYKeyGenerator arg0);
	
}