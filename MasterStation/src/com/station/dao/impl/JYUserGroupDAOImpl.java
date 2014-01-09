package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.station.constant.Constant;
import com.station.dao.JYUserGroupDAO;
import com.station.po.JYUserGroup;


public class JYUserGroupDAOImpl extends HibernateDaoSupport implements JYUserGroupDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<JYUserGroup> findJYUserGroupByHql(String hql) {
		//String hql = "from JYUserGroup arg0";
		return (List<JYUserGroup>) this.getHibernateTemplate().find(hql);
	}
	@Override
	public JYUserGroup findJYUserGroupById(Integer id) {
		JYUserGroup arg0 = (JYUserGroup) this.getHibernateTemplate().get(JYUserGroup.class, id);
		return arg0;
	}
	@Override
	public void removeJYUserGroup(JYUserGroup arg0) {
		this.getHibernateTemplate().delete(arg0);
	}
	@Override
	public void saveJYUserGroup(JYUserGroup arg0) {
		this.getHibernateTemplate().save(arg0);
	}
	@Override
	public void updateJYUserGroup(JYUserGroup arg0) {
		this.getHibernateTemplate().update(arg0);
	}


	
	/**
	 * 查询总记录数
	 */
	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		String hql0 = "select count(*) "+hql;  
		Query query =  this.getSession().createQuery(hql0);  
		query = Constant.setParameters(query, parameters);
		return ((Long)query.uniqueResult()).intValue(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYUserGroup> getPerPage(final String hql, final int startRow, final int countPerpage,final Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		List<JYUserGroup> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query = Constant.setParameters(query, parameters);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYUserGroup> list=query.list();
				return list;
			}			
		});
		return list;
	}
}