package com.station.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYConstantDAO;
import com.station.po.JYConstant;


public class JYConstantDAOImpl extends HibernateDaoSupport implements JYConstantDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYConstant> findJYConstantByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYConstant>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYConstant findJYConstantById(Integer id) {
		// TODO Auto-generated method stub
		JYConstant data = (JYConstant) this.getHibernateTemplate().get(JYConstant.class, id);
		return data;
	}

	@Override
	public void removeJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	@Override
	public void saveJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYConstant(JYConstant arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

}