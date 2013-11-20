package com.station.dao.impl;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYKeyGeneratorDAO;
import com.station.po.JYKeyGenerator;


public class JYKeyGeneratorDAOImpl extends HibernateDaoSupport implements JYKeyGeneratorDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYKeyGenerator> findJYKeyGeneratorByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYKeyGenerator>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYKeyGenerator findJYKeyGeneratorById(String id) {
		// TODO Auto-generated method stub
		JYKeyGenerator data = (JYKeyGenerator) this.getHibernateTemplate().get(JYKeyGenerator.class, id);
		return data;
	}

	@Override
	public void removeJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	@Override
	public void saveJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYKeyGenerator(JYKeyGenerator arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

}