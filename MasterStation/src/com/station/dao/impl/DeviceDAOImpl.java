package com.station.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.station.dao.DeviceDAO;
import com.station.po.Device;



public class DeviceDAOImpl extends HibernateDaoSupport implements DeviceDAO {

	@SuppressWarnings("unchecked")
	public List<Device> findAllDevice() {
		String hql = "from Device device";
		return (List<Device>) this.getHibernateTemplate().find(hql);
	}

	public Device findDeviceById(Integer id) {
		Device data = (Device) this.getHibernateTemplate().get(Device.class, id);
		return data;
	}

	public void removeDevice(Device data) {
		this.getHibernateTemplate().delete(data);
	}

	public void saveDevice(Device data) {
		this.getHibernateTemplate().save(data);
	}

	public void updateDevice(Device data) {
		this.getHibernateTemplate().update(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> findDevicesByBox(String box) {
		// TODO Auto-generated method stub
		String hql = "from Device device where DEVICE_BOX = '"+box+"'";
		return (List<Device>) this.getHibernateTemplate().find(hql);
	}

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Device> findDevicesByOwner(String owner) {
		// TODO Auto-generated method stub
		String hql = "from Device device where OWNER = '"+owner+"' or OWNER = '-'";
		return (List<Device>) this.getHibernateTemplate().find(hql);
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> getPerPage(final String hql, final int startRow, final int countPerpage) {
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

	@SuppressWarnings("unchecked")
	@Override
	public Device findDeviceByIdentify(String identify) {
		// TODO Auto-generated method stub
		String hql = "from Device device where identify = '"+identify+"'";
		List list = (List<Device>) this.getHibernateTemplate().find(hql);
		if (list!=null&&list.size()>0)
			return (Device) list.get(0);
		else
			return null;
	}

}