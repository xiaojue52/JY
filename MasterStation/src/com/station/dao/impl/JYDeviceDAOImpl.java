package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYDeviceDAO;
import com.station.po.JYDevice;


public class JYDeviceDAOImpl extends HibernateDaoSupport implements JYDeviceDAO {	
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
	public List<JYDevice> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYDevice> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYDevice> list=query.list();
				return list;
			}			
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYDevice> findJYDeviceByHql(String hql) {
		// TODO Auto-generated method stub
		//String hql = "from JYDevice cabinet";
		/*Configuration config = new Configuration().configure(); 
		SessionFactory factory = config.buildSessionFactory(); 
		Session session = factory.openSession(); 
		Query query = session.createQuery("from JYDevice cabinet"); 
		List<JYDevice> list = query.list(); 
		return list;*/
		return (List<JYDevice>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYDevice findJYDeviceById(String id) {
		// TODO Auto-generated method stub
		JYDevice data = (JYDevice) this.getHibernateTemplate().get(JYDevice.class, id);
		return data;
	}

	@Override
	public void removeJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
		
	}

	@Override
	public void saveJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYDevice(JYDevice arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}
}