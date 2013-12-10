package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYHistoryMonthChartDataDAO;
import com.station.po.JYHistoryMonthChartData;


public class JYHistoryMonthChartDataDAOImpl extends HibernateDaoSupport implements JYHistoryMonthChartDataDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<JYHistoryMonthChartData> findJYHistoryMonthChartDataByHql(String hql) {
		//String hql = "from JYHistoryMonthChartData user";
		return (List<JYHistoryMonthChartData>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public JYHistoryMonthChartData findJYHistoryMonthChartDataById(String id) {
		JYHistoryMonthChartData user = (JYHistoryMonthChartData) this.getHibernateTemplate().get(JYHistoryMonthChartData.class, id);
		return user;
	}
	@Override
	public void removeJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
		this.getHibernateTemplate().delete(arg0);
	}
	@Override
	public void saveJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
		this.getHibernateTemplate().save(arg0);
	}
	@Override
	public void updateJYHistoryMonthChartData(JYHistoryMonthChartData arg0) {
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
	public List<JYHistoryMonthChartData> getPerPage(final String hql, final int startRow, final int countPerpage) {
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