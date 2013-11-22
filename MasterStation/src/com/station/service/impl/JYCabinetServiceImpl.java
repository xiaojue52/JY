package com.station.service.impl;

import java.util.List;

import com.station.constant.Constant;
import com.station.dao.JYAlarmTypeCollectDAO;
import com.station.dao.JYAlarmTypeDAO;
import com.station.dao.JYCabinetDAO;
import com.station.dao.JYConstantDAO;
import com.station.dao.JYLineDAO;
import com.station.dao.JYUserDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYCabinet;
import com.station.po.JYUser;
import com.station.service.JYCabinetService;

public class JYCabinetServiceImpl implements JYCabinetService {

	private JYCabinetDAO cabinetDAO;
	private JYUserDAO userDAO;
	private JYLineDAO lineDAO;
	private JYAlarmTypeDAO alarmTypeDAO;
	private JYAlarmTypeCollectDAO alarmTypeCollectDAO;
	private JYConstantDAO constantDAO;

	public void setUserDAO(JYUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public void setLineDAO(JYLineDAO lineDAO) {
		this.lineDAO = lineDAO;
	}

	public void setAlarmTypeDAO(JYAlarmTypeDAO alarmTypeDAO) {
		this.alarmTypeDAO = alarmTypeDAO;
	}

	public void setAlarmTypeCollectDAO(JYAlarmTypeCollectDAO alarmTypeCollectDAO) {
		this.alarmTypeCollectDAO = alarmTypeCollectDAO;
	}

	public void setConstantDAO(JYConstantDAO constantDAO) {
		this.constantDAO = constantDAO;
	}

	public void setCabinetDAO(JYCabinetDAO cabinetDAO) {
		this.cabinetDAO = cabinetDAO;
	}

	/**
	 * 分页查询
	 */
	@Override
	public PageBean getPerPage(int countPerpage, int currentPage, String hql) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=cabinetDAO.getTotalCount(hql);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYCabinet> list= cabinetDAO.getPerPage(hql, startRow, CountPerpage);//该页显示的记录
		
		PageBean pageBean=new PageBean();
		pageBean.setCurrentPage(CurrentPage);
		pageBean.setCountPerpage(CountPerpage);
		pageBean.setTotalCount(TotalCount);
		pageBean.setTotalPage(TotalPage);
		pageBean.setList(list);
		pageBean.init();		
		
		return pageBean;
	}

	@Override
	public List<JYCabinet> findJYCabinetByHql(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.findJYCabinetByHql(hql);
	}

	@Override
	public JYCabinet findJYCabinetById(String id) {
		// TODO Auto-generated method stub
		return cabinetDAO.findJYCabinetById(id);
	}

	@Override
	public void removeJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		//cabinetDAO.removeJYCabinet(arg0);
		arg0.setTag(0);
		JYUser user  =  this.userDAO.findUserByHql("from JYUser user where user.username = '--'").get(0);
		arg0.setUser(user);
		cabinetDAO.updateJYCabinet(arg0);
	}

	@Override
	public void saveJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		
		JYCabinet cabinet = arg0;
		cabinet.setTag(1);
		if (cabinet.getUser()!=null&&userDAO.findUserById(cabinet.getUser().getUserId())!=null){
			cabinet.setUser(userDAO.findUserById(cabinet.getUser().getUserId()));
		}
		cabinet.setLine(lineDAO.findLineById(cabinet.getLine().getLineId()));
		cabinet.setPowerLevel(constantDAO.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantDAO.findJYConstantById(cabinet.getCabType().getId()));

		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectDAO.findJYAlarmTypeCollectById("-1");
		}
		else
		{
			alarmTypeCollect = cabinet.getAlarmTypeCollect();
			alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getCurrentDateStr());
			JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
			JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
			JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
			alarmType1.setId(alarmTypeCollect.getId()+1000);
			alarmType1.setType(this.constantDAO.findJYConstantByHql(Constant.alarmType1Hql).get(0));
			alarmType2.setId(alarmTypeCollect.getId()+1001);
			alarmType2.setType(this.constantDAO.findJYConstantByHql(Constant.alarmType2Hql).get(0));
			alarmType3.setId(alarmTypeCollect.getId()+1002);
			alarmType3.setType(this.constantDAO.findJYConstantByHql(Constant.alarmType3Hql).get(0));
		}
		cabinet.setAlarmTypeCollect(alarmTypeCollect);
		cabinetDAO.saveJYCabinet(cabinet);
	}

	@Override
	public void updateJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		JYCabinet cabinet = arg0;
		cabinet.setTag(1);
		cabinet.setLine(lineDAO.findLineById(cabinet.getLine().getLineId()));
		cabinet.setUser(userDAO.findUserById(cabinet.getUser().getUserId()));
		cabinet.setPowerLevel(constantDAO.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantDAO.findJYConstantById(cabinet.getCabType().getId()));
		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectDAO.findJYAlarmTypeCollectById("-1");
			if (cabinet.getAlarmTypeCollect().getId().equals("-1")){
				cabinet.setAlarmTypeCollect(alarmTypeCollect);
				cabinetDAO.updateJYCabinet(cabinet);
				return;
			}
			JYAlarmTypeCollect collect = cabinet.getAlarmTypeCollect();
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
			cabinetDAO.updateJYCabinet(cabinet);
			JYAlarmType type1 = collect.getAlarmType1();
			JYAlarmType type2 = collect.getAlarmType2();
			JYAlarmType type3 = collect.getAlarmType3();
			collect.setAlarmType1(null);
			collect.setAlarmType2(null);
			collect.setAlarmType3(null);
			this.alarmTypeCollectDAO.updateJYAlarmTypeCollect(collect);
			this.alarmTypeCollectDAO.removeJYAlarmTypeCollect(collect);
			type1.setType(null);
			type2.setType(null);
			type3.setType(null);
			this.alarmTypeDAO.updateJYAlarmType(type1);
			this.alarmTypeDAO.updateJYAlarmType(type2);
			this.alarmTypeDAO.updateJYAlarmType(type3);
			this.alarmTypeDAO.removeJYAlarmType(type1);
			this.alarmTypeDAO.removeJYAlarmType(type2);
			this.alarmTypeDAO.removeJYAlarmType(type3);
			return;
			//cabinet.getAlarmTypeCollect().setAlarmType1(null);
			//cabinet.setAlarmTypeCollect(alarmTypeCollect);
		}else if(cabinet.getAlarmTypeCollect().getId().equals("-1")){
			alarmTypeCollect = new JYAlarmTypeCollect();
			alarmTypeCollect = cabinet.getAlarmTypeCollect();
			alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getCurrentDateStr());
			JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
			JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
			JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
			alarmType1.setId(alarmTypeCollect.getId()+1000);
			alarmType2.setId(alarmTypeCollect.getId()+1001);
			alarmType3.setId(alarmTypeCollect.getId()+1002);
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
		}
		
		cabinetDAO.updateJYCabinet(cabinet);
	}

	@Override
	public int getTotalCount(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.getTotalCount(hql);
	}
}
