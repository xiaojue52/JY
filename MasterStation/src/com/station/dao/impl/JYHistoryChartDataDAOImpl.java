package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYHistoryChartDataDAO;
import com.station.po.JYHistoryChartData;


public class JYHistoryChartDataDAOImpl extends HibernateDaoSupport implements JYHistoryChartDataDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<JYHistoryChartData> findJYHistoryChartDataByHql(String hql) {
		//String hql = "from JYHistoryChartData user";
		return (List<JYHistoryChartData>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public JYHistoryChartData findJYHistoryChartDataById(String id) {
		JYHistoryChartData user = (JYHistoryChartData) this.getHibernateTemplate().get(JYHistoryChartData.class, id);
		return user;
	}
	@Override
	public void removeJYHistoryChartData(JYHistoryChartData arg0) {
		this.getHibernateTemplate().delete(arg0);
	}
	@Override
	public void saveJYHistoryChartData(JYHistoryChartData arg0) {
		this.getHibernateTemplate().save(arg0);
	}
	@Override
	public void updateJYHistoryChartData(JYHistoryChartData arg0) {
		this.getHibernateTemplate().update(arg0);
	}


	
	/**
	 * 查询总记录数
	 */
	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql).size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYHistoryChartData> getPerPage(final String hql, final int startRow, final int countPerpage) {
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
}