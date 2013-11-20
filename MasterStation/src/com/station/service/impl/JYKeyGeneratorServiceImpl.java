package com.station.service.impl;

import java.util.List;
import com.station.dao.JYKeyGeneratorDAO;
import com.station.po.JYKeyGenerator;
import com.station.service.JYKeyGeneratorService;

public class JYKeyGeneratorServiceImpl implements JYKeyGeneratorService {

	private JYKeyGeneratorDAO keyGeneratorDAO;

	public void setKeyGeneratorDAO(JYKeyGeneratorDAO keyGeneratorDAO) {
		this.keyGeneratorDAO = keyGeneratorDAO;
	}

	@Override
	public List<JYKeyGenerator> findJYKeyGeneratorByHql(String hql) {
		// TODO Auto-generated method stub
		return keyGeneratorDAO.findJYKeyGeneratorByHql(hql);
	}

	@Override
	public JYKeyGenerator findJYKeyGeneratorById(String id) {
		// TODO Auto-generated method stub
		return keyGeneratorDAO.findJYKeyGeneratorById(id);
	}

	@Override
	public void removeJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		keyGeneratorDAO.removeJYKeyGenerator(arg0);
	}

	@Override
	public void saveJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		keyGeneratorDAO.saveJYKeyGenerator(arg0);
	}

	@Override
	public void updateJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		keyGeneratorDAO.updateJYKeyGenerator(arg0);
	}
}
