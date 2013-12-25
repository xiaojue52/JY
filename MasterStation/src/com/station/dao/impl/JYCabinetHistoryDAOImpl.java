package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYCabinetHistoryDAO;
import com.station.po.JYCabinetHistory;


public class JYCabinetHistoryDAOImpl extends HibernateDaoSupport implements JYCabinetHistoryDAO {	
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
	public List<JYCabinetHistory> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYCabinetHistory> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYCabinetHistory> list=query.list();
				return list;
			}			
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYCabinetHistory> findJYCabinetHistoryByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYCabinetHistory>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYCabinetHistory findJYCabinetHistoryById(String id) {
		// TODO Auto-generated method stub
		JYCabinetHistory data = (JYCabinetHistory) this.getHibernateTemplate().get(JYCabinetHistory.class, id);
		return data;
	}

	@Override
	public void removeJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
		
	}

	@Override
	public void saveJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYCabinetHistory(JYCabinetHistory arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}
}