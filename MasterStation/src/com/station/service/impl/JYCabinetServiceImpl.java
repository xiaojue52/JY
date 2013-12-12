package com.station.service.impl;

import java.util.List;

import com.station.constant.Constant;
import com.station.dao.JYCabinetDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYCabinet;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.po.JYUser;
import com.station.service.JYAlarmTypeCollectService;
import com.station.service.JYAlarmTypeService;
import com.station.service.JYCabinetService;
import com.station.service.JYConstantService;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;
import com.station.service.JYLineService;
import com.station.service.JYUserService;

public class JYCabinetServiceImpl implements JYCabinetService {

	private JYCabinetDAO cabinetDAO;
	private JYUserService userService;
	private JYLineService lineService;
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmTypeCollectService alarmTypeCollectService;
	private JYConstantService constantService;
	private JYDeviceService deviceService;
	private JYDetectorService detectorService;


	public void setDetectorService(JYDetectorService detectorService) {
		this.detectorService = detectorService;
	}

	public void setDeviceService(JYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}

	public void setLineService(JYLineService lineService) {
		this.lineService = lineService;
	}

	public void setAlarmTypeService(JYAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
	}

	public void setAlarmTypeCollectService(
			JYAlarmTypeCollectService alarmTypeCollectService) {
		this.alarmTypeCollectService = alarmTypeCollectService;
	}

	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
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
		for (int i=0;i<list.size();i++){
			String deviceHql = "from JYDevice device where device.cabinet.cabId = '"+list.get(i).getCabId()+"'";
			List<JYDevice> deviceList = deviceService.findJYDeviceByHql(deviceHql);
			list.get(i).setDeviceList(deviceList);
			for (int j=0;j<deviceList.size();j++){
				String detectorHql = "from JYDetector detector where detector.device.deviceId = '"+deviceList.get(j).getDeviceId()+"'";
				List<JYDetector> detectorList = this.detectorService.findJYDetectorByHql(detectorHql);
				deviceList.get(j).setDetectorList(detectorList);
			}
		}
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
		JYUser user  =  this.userService.findUserByHql("from JYUser user where user.username = '--'").get(0);
		arg0.setUser(user);
		cabinetDAO.updateJYCabinet(arg0);
		String hql = "from JYDevice device where device.cabinet.cabId = '"+arg0.getCabId()+"'";
		List<JYDevice> list = this.deviceService.findJYDeviceByHql(hql);
		this.deviceService.removeJYDevices(list);
	}
	@Override
	public void removeJYCabinets(List<JYCabinet> list){
		for (int i=0;i<list.size();i++){
			this.removeJYCabinet(list.get(i));
		}
	}
	
	@Override
	public void saveJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		
		JYCabinet cabinet = arg0;
		cabinet.setTag(1);
		if (cabinet.getUser()!=null&&userService.findUserById(cabinet.getUser().getUserId())!=null){
			cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		}
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setPowerLevel(constantService.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantService.findJYConstantById(cabinet.getCabType().getId()));

		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");
		}
		else
		{
			alarmTypeCollect = cabinet.getAlarmTypeCollect();
			alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getCurrentDateStr());
			JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
			JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
			JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
			alarmType1.setId(alarmTypeCollect.getId()+1000);
			alarmType1.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE1HQL).get(0));
			alarmType2.setId(alarmTypeCollect.getId()+1001);
			alarmType2.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE2HQL).get(0));
			alarmType3.setId(alarmTypeCollect.getId()+1002);
			alarmType3.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE3HQL).get(0));
		}
		cabinet.setAlarmTypeCollect(alarmTypeCollect);
		cabinetDAO.saveJYCabinet(cabinet);
	}

	@Override
	public void updateJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		JYCabinet cabinet = arg0;
		cabinet.setTag(1);
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		cabinet.setPowerLevel(constantService.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantService.findJYConstantById(cabinet.getCabType().getId()));
		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");
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
			this.alarmTypeCollectService.updateJYAlarmTypeCollect(collect);
			this.alarmTypeCollectService.removeJYAlarmTypeCollect(collect);
			type1.setType(null);
			type2.setType(null);
			type3.setType(null);
			this.alarmTypeService.updateJYAlarmType(type1);
			this.alarmTypeService.updateJYAlarmType(type2);
			this.alarmTypeService.updateJYAlarmType(type3);
			this.alarmTypeService.removeJYAlarmType(type1);
			this.alarmTypeService.removeJYAlarmType(type2);
			this.alarmTypeService.removeJYAlarmType(type3);
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
