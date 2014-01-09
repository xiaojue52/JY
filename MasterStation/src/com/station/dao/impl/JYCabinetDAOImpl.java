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
import com.station.dao.JYCabinetDAO;
import com.station.po.JYCabinet;


public class JYCabinetDAOImpl extends HibernateDaoSupport implements JYCabinetDAO {	
	/**
	 * 查询总记录数
	 */
	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		String hql0 = "select count(*) "+hql;  
		Query query =  this.getSession().createQuery(hql0); 
		query = Constant.setParameters(query,parameters);
		return ((Long)query.uniqueResult()).intValue(); 
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYCabinet> getPerPage(final String hql, final int startRow, final int countPerpage,final Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		List<JYCabinet> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query = Constant.setParameters(query,parameters);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYCabinet> list=query.list();
				return list;
			}			
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYCabinet> findJYCabinetByHql(String hql) {
		// TODO Auto-generated method stub
		return (List<JYCabinet>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYCabinet findJYCabinetById(String id) {
		// TODO Auto-generated method stub
		JYCabinet data = (JYCabinet) this.getHibernateTemplate().get(JYCabinet.class, id);
		return data;
	}

	@Override
	public void removeJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
		
	}

	@Override
	public String saveJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		return (String)this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}
}