package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.station.dao.DeviceHistoryDAO;
import com.station.po.DeviceHistory;



public class DeviceHistoryDAOImpl extends HibernateDaoSupport implements DeviceHistoryDAO {

	@SuppressWarnings("unchecked")
	public List<DeviceHistory> findAllDevice() {
		String hql = "from DeviceHistory deviceHistory";
		return (List<DeviceHistory>) this.getHibernateTemplate().find(hql);
	}

	public DeviceHistory findDeviceById(Integer id) {
		DeviceHistory data = (DeviceHistory) this.getHibernateTemplate().get(DeviceHistory.class, id);
		return data;
	}

	public void removeDevice(DeviceHistory data) {
		this.getHibernateTemplate().delete(data);
	}

	public void saveDevice(DeviceHistory data) {
		this.getHibernateTemplate().save(data);
	}

	public void updateDevice(DeviceHistory data) {
		this.getHibernateTemplate().update(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceHistory> findDevicesByLine(String line, String owner) {
		// TODO Auto-generated method stub
		String hql = "from DeviceHistory deviceHistory where DEVICE_BOX = '"+line+"' and OWNER = '"+owner+"'";
		return (List<DeviceHistory>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceHistory> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		String hql = "from DeviceHistory deviceHistory where OWNER = '"+owner+"'";
		return (List<DeviceHistory>) this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DeviceHistory> getPerPage(final String hql, final int startRow, final int countPerpage) {
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

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql).size();
	}

}