package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.station.dao.JYUserDAO;
import com.station.po.JYUser;


public class JYUserDAOImpl extends HibernateDaoSupport implements JYUserDAO {

	@SuppressWarnings("unchecked")
	public List<JYUser> findUserByHql(String hql) {
		//String hql = "from JYUser user";
		return (List<JYUser>) this.getHibernateTemplate().find(hql);
	}

	public JYUser findUserById(String id) {
		JYUser user = (JYUser) this.getHibernateTemplate().get(JYUser.class, id);
		return user;
	}

	public void removeUser(JYUser user) {
		this.getHibernateTemplate().delete(user);
	}

	public void saveUser(JYUser user) {
		this.getHibernateTemplate().save(user);
	}

	public void updateUser(JYUser user) {
		this.getHibernateTemplate().update(user);
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
	public List<JYUser> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYUser> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYUser> list=query.list();
				return list;
			}			
		});
		return list;
	}
}