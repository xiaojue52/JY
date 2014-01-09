package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.station.dao.JYHistoryDAO;
import com.station.po.JYHistory;


public class JYHistoryDAOImpl extends HibernateDaoSupport implements JYHistoryDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<JYHistory> findJYHistoryByHql(String hql) {
		//String hql = "from JYHistory user";
		return (List<JYHistory>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public JYHistory findJYHistoryById(String id) {
		JYHistory user = (JYHistory) this.getHibernateTemplate().get(JYHistory.class, id);
		return user;
	}
	@Override
	public void removeJYHistory(JYHistory arg0) {
		this.getHibernateTemplate().delete(arg0);
	}
	@Override
	public void saveJYHistory(JYHistory arg0) {
		this.getHibernateTemplate().save(arg0);
	}
	@Override
	public void updateJYHistory(JYHistory arg0) {
		this.getHibernateTemplate().update(arg0);
	}


	
	/**
	 * 查询总记录数
	 */
	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		String hql0 = "select count(*) "+hql;  
		Query query =  this.getSession().createQuery(hql0);  
		return ((Long)query.uniqueResult()).intValue(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYHistory> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYHistory> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYHistory> list=query.list();
				return list;
			}			
		});
		return list;
	}
}