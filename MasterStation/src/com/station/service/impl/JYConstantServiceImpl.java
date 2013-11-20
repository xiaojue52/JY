package com.station.service.impl;

import java.util.List;
import com.station.dao.JYConstantDAO;
import com.station.po.JYConstant;
import com.station.service.JYConstantService;

public class JYConstantServiceImpl implements JYConstantService {

	private JYConstantDAO constantDAO;

	public void setConstantDAO(JYConstantDAO constantDAO) {
		this.constantDAO = constantDAO;
	}

	@Override
	public List<JYConstant> findJYConstantByHql(String hql) {
		// TODO Auto-generated method stub
		return constantDAO.findJYConstantByHql(hql);
	}

	@Override
	public JYConstant findJYConstantById(Integer id) {
		// TODO Auto-generated method stub
		return constantDAO.findJYConstantById(id);
	}

	@Override
	public void removeJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		constantDAO.removeJYConstant(arg0);
	}

	@Override
	public void saveJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		constantDAO.saveJYConstant(arg0);
	}

	@Override
	public void updateJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		constantDAO.updateJYConstant(arg0);
	}
}
