package com.station.service.impl;

import java.util.List;
import com.station.dao.JYAlarmTypeDAO;
import com.station.po.JYAlarmType;
import com.station.service.JYAlarmTypeService;

public class JYAlarmTypeServiceImpl implements JYAlarmTypeService {

	private JYAlarmTypeDAO alarmTypeDAO;

	public void setAlarmTypeDAO(JYAlarmTypeDAO alarmTypeDAO) {
		this.alarmTypeDAO = alarmTypeDAO;
	}

	@Override
	public List<JYAlarmType> findJYAlarmTypeByHql(String hql) {
		// TODO Auto-generated method stub
		return alarmTypeDAO.findJYAlarmTypeByHql(hql);
	}

	@Override
	public JYAlarmType findJYAlarmTypeById(String id) {
		// TODO Auto-generated method stub
		return alarmTypeDAO.findJYAlarmTypeById(id);
	}

	@Override
	public void removeJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		alarmTypeDAO.removeJYAlarmType(arg0);
	}

	@Override
	public void saveJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		alarmTypeDAO.saveJYAlarmType(arg0);
	}

	@Override
	public void updateJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		alarmTypeDAO.updateJYAlarmType(arg0);
	}
}
