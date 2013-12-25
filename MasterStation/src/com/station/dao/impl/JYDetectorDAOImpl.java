package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.JYDetectorDAO;
import com.station.po.JYDetector;


public class JYDetectorDAOImpl extends HibernateDaoSupport implements JYDetectorDAO {	
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
	public List<JYDetector> getPerPage(final String hql, final int startRow, final int countPerpage) {
		// TODO Auto-generated method stub
		List<JYDetector> list=getHibernateTemplate().executeFind(new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				// TODO Auto-generated method stub
				Query query=session.createQuery(hql);
				query.setFirstResult(startRow);
				query.setMaxResults(countPerpage);
				List<JYDetector> list=query.list();
				return list;
			}			
		});
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<JYDetector> findJYDetectorByHql(String hql) {
		// TODO Auto-generated method stub
		//String hql = "from JYDetector cabinet";
		/*Configuration config = new Configuration().configure(); 
		SessionFactory factory = config.buildSessionFactory(); 
		Session session = factory.openSession(); 
		Query query = session.createQuery("from JYDetector cabinet"); 
		List<JYDetector> list = query.list(); 
		return list;*/
		return (List<JYDetector>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public JYDetector findJYDetectorById(String id) {
		// TODO Auto-generated method stub
		JYDetector data = (JYDetector) this.getHibernateTemplate().get(JYDetector.class, id);
		return data;
	}

	@Override
	public void removeJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(arg0);
		
	}

	@Override
	public void saveJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(arg0);
	}

	@Override
	public void updateJYDetector(JYDetector arg0) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(arg0);
	}
}