package com.station.service.impl;

import java.util.List;
import com.station.dao.JYConstantDAO;
import com.station.po.JYCabinet;
import com.station.po.JYConstant;
import com.station.service.JYCabinetService;
import com.station.service.JYConstantService;

public class JYConstantServiceImpl implements JYConstantService {

	private JYConstantDAO constantDAO;
	private JYCabinetService cabinetService;

	public JYCabinetService getCabinetService() {
		return cabinetService;
	}

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}

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
	public int removeJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		String hql = "from JYCabinet cabinet where cabinet.cabType.id = '"+arg0.getId()+"'";
		List<JYCabinet> list = this.cabinetService.findJYCabinetByHql(hql);
		if(list!=null&&list.size()>0)
			return -1;
		else
			constantDAO.removeJYConstant(arg0);
		return 1;
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
