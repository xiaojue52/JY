package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYAlarmDAO;
import com.station.po.JYAlarm;


public class JYAlarmDAOImpl extends HibernateDaoSupport implements JYAlarmDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYAlarm> findJYAlarmByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYAlarm>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYAlarm findJYAlarmById(String id) {
		// TODO Auto-generated method stub
		JYAlarm data = (JYAlarm) this.getHibernateTemplate().get(JYAlarm.class, id);
		return data;
	}

	@Override
	public void removeJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
	}

	@Override
	public void saveJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYAlarm(JYAlarm arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYAlarm> getPerPage(final String hql,final int startRow,final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYAlarm> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYAlarm> list=query.list();
				return list;
			}			
		});
		return list;
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		String hql0 = "select count(*) "+hql;  
		Query query =  this.getSession().createQuery(hql0);  
		return ((Long)query.uniqueResult()).intValue(); 
	}
	@Override
	public void removeMultiplAlarms(String hql){
		//this.getSession().createQuery(hql);
		Session session = this.getSession();
		SQLQuery query = session.createSQLQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findCostomizeObjHql(String hql) {
		// TODO Auto-generated method stub
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

}