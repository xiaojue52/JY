package com.station.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYAlarmTypeDAO;
import com.station.po.JYAlarmType;


public class JYAlarmTypeDAOImpl extends HibernateDaoSupport implements JYAlarmTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYAlarmType> findJYAlarmTypeByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYAlarmType>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYAlarmType findJYAlarmTypeById(String id) {
		// TODO Auto-generated method stub
		JYAlarmType data = (JYAlarmType) this.getHibernateTemplate().get(JYAlarmType.class, id);
		return data;
	}

	@Override
	public void removeJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	@Override
	public void saveJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYAlarmType(JYAlarmType arg0) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		JYAlarmType type = (JYAlarmType)session.merge(arg0);
		session.save(type);
		//this.getHibernateTemplate().update(arg0);
	}

}