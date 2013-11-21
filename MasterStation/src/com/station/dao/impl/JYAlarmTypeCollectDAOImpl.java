package com.station.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYAlarmTypeCollectDAO;
import com.station.po.JYAlarmTypeCollect;


public class JYAlarmTypeCollectDAOImpl extends HibernateDaoSupport implements JYAlarmTypeCollectDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYAlarmTypeCollect> findJYAlarmTypeCollectByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYAlarmTypeCollect>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYAlarmTypeCollect findJYAlarmTypeCollectById(String id) {
		// TODO Auto-generated method stub
		JYAlarmTypeCollect data = (JYAlarmTypeCollect) this.getHibernateTemplate().get(JYAlarmTypeCollect.class, id);
		return data;
	}

	@Override
	public void removeJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	@Override
	public void saveJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYAlarmTypeCollect(JYAlarmTypeCollect arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

}