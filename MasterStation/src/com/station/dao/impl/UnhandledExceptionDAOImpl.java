package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.UnhandledExceptionDAO;
import com.station.po.UnhandledException;



public class UnhandledExceptionDAOImpl extends HibernateDaoSupport implements UnhandledExceptionDAO {

	@SuppressWarnings("unchecked")
	public List<UnhandledException> findAllDevice() {
		String hql = "from UnhandledException unhandledException";
		return (List<UnhandledException>) this.getHibernateTemplate().find(hql);
	}

	public UnhandledException findDeviceById(Integer id) {
		UnhandledException data = (UnhandledException) this.getHibernateTemplate().get(UnhandledException.class, id);
		return data;
	}

	public void removeDevice(UnhandledException data) {
		this.getHibernateTemplate().delete(data);
	}

	public void saveDevice(UnhandledException data) {
		this.getHibernateTemplate().save(data);
	}

	public void updateDevice(UnhandledException data) {
		this.getHibernateTemplate().update(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UnhandledException> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		String hql = "from UnhandledException unhandledException where OWNER = '"+owner+"'";
		return (List<UnhandledException>) this.getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UnhandledException> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List list=query.list();
				return list;
			}			
		});
		return list;
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql).size();
	}
}