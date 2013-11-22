package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYLineDAO;
import com.station.po.JYLine;


public class JYLineDAOImpl extends HibernateDaoSupport implements JYLineDAO {	
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
	public List<JYLine> getPerPage(final String hql, final int startRow, final int countPerpage) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<JYLine> findAllLineByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYLine>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYLine findLineById(String id) {
		// TODO Auto-generated method stub
		JYLine data = (JYLine) this.getHibernateTemplate().get(JYLine.class, id);
		return data;
	}

	@Override
	public void removeLine(JYLine arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
		
	}

	@Override
	public void saveLine(JYLine arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateLine(JYLine arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}
}