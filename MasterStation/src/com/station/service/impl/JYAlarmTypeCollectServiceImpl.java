package com.station.service.impl;

import java.util.List;
import com.station.dao.JYAlarmTypeCollectDAO;
import com.station.po.JYAlarmTypeCollect;
import com.station.service.JYAlarmTypeCollectService;

public class JYAlarmTypeCollectServiceImpl implements JYAlarmTypeCollectService {

	private JYAlarmTypeCollectDAO alarmTypeCollectDAO;

	public void setAlarmTypeCollectDAO(JYAlarmTypeCollectDAO alarmTypeCollectDAO) {
		this.alarmTypeCollectDAO = alarmTypeCollectDAO;
	}

	@Override
	public List<JYAlarmTypeCollect> findJYAlarmTypeCollectByHql(String hql) {
		// TODO Auto-generated method stub
		return alarmTypeCollectDAO.findJYAlarmTypeCollectByHql(hql);
	}

	@Override
	public JYAlarmTypeCollect findJYAlarmTypeCollectById(String id) {
		// TODO Auto-generated method stub
		return alarmTypeCollectDAO.findJYAlarmTypeCollectById(id);
	}

	@Override
	public void removeJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		alarmTypeCollectDAO.removeJYAlarmTypeCollect(arg0);
	}

	@Override
	public void saveJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		alarmTypeCollectDAO.saveJYAlarmTypeCollect(arg0);
	}

	@Override
	public void updateJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		alarmTypeCollectDAO.updateJYAlarmTypeCollect(arg0);
	}
}
