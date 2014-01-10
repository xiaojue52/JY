package com.station.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.station.constant.Constant;
import com.station.dao.JYCabinetDAO;
import com.station.pagebean.PageBean;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYCabinet;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.service.JYAlarmTypeCollectService;
import com.station.service.JYAlarmTypeService;
import com.station.service.JYCabinetService;
import com.station.service.JYConstantService;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;
import com.station.socket.SocketRoute;

public class JYCabinetServiceImpl implements JYCabinetService {

	private JYCabinetDAO cabinetDAO;
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmTypeCollectService alarmTypeCollectService;
	private JYConstantService constantService;
	private JYDeviceService deviceService;
	private JYDetectorService detectorService;
	private static SocketRoute socketRoute;


	public static void setSocketRoute(SocketRoute socketRoute) {
		JYCabinetServiceImpl.socketRoute = socketRoute;
	}

	public void setDetectorService(JYDetectorService detectorService) {
		this.detectorService = detectorService;
	}

	public void setDeviceService(JYDeviceService deviceService) {
		this.deviceService = deviceService;
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
	public PageBean getPerPage(int countPerpage, int currentPage, String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		//final String hql="from JYUser user where user_level = 'user' or user_level = 'com_admin' order by id desc";
		int TotalCount=cabinetDAO.getTotalCount(hql,parameters);//总记录数		
		int TotalPage=PageBean.countTotalPage(countPerpage, TotalCount);//总页数
		final int startRow=PageBean.countStartRow(countPerpage, currentPage);//当前页开始的行
		final int CountPerpage=countPerpage;//每页显示的记录数
		final int CurrentPage=PageBean.countCurrentPage(currentPage);//当前页面
		
		List<JYCabinet> list= cabinetDAO.getPerPage(hql, startRow, CountPerpage,parameters);//该页显示的记录
		for (int i=0;i<list.size();i++){
			String deviceHql = "from JYDevice device where tag = 1 and device.cabinet.cabId = '"+list.get(i).getCabId()+"'";
			List<JYDevice> deviceList = deviceService.findJYDeviceByHql(deviceHql);
			list.get(i).setDeviceList(deviceList);
			for (int j=0;j<deviceList.size();j++){
				String detectorHql = "from JYDetector detector where tag = 1 and detector.device.deviceId = '"+deviceList.get(j).getDeviceId()+"'";
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
		cabinetDAO.updateJYCabinet(arg0);
		String hql = "from JYDevice device where device.cabinet.cabId = '"+arg0.getCabId()+"'";
		List<JYDevice> list = this.deviceService.findJYDeviceByHql(hql);
		this.deviceService.removeJYDevices(list);
		socketRoute.removedCabinet(arg0.getCabId());
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
		JYAlarmTypeCollect alarmTypeCollect = null;
		if (this.isGlobalAlarmCollection(cabinet)){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");
		}
		else
		{
			alarmTypeCollect = this.createAlarmCollection(cabinet);
		}
		cabinet.setAlarmTypeCollect(alarmTypeCollect);
		String cabId = cabinetDAO.saveJYCabinet(cabinet);
		if(cabinet.getStatus()==1)
			socketRoute.addCabinet(cabId);
	}

	@Override
	public void updateJYCabinet(JYCabinet arg0) {
		// TODO Auto-generated method stub
		JYCabinet cabinet = arg0;
		if(cabinet.getStatus()!=1)
			socketRoute.removedCabinet(cabinet.getCabId());
		else
			socketRoute.addCabinet(cabinet.getCabId());	
		cabinet.setTag(1);
		JYAlarmTypeCollect alarmTypeCollect = null;
		if (this.isGlobalAlarmCollection(cabinet)){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");
			if (!cabinet.getAlarmTypeCollect().getId().equals("-1")){
				this.removedAlarmCollection(cabinet);
			}
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
			cabinetDAO.updateJYCabinet(cabinet);
			return;
		}else if(cabinet.getAlarmTypeCollect().getId().equals("-1")){
			alarmTypeCollect = this.createAlarmCollection(cabinet);
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
			cabinetDAO.updateJYCabinet(cabinet);
		}else
		{
			this.saveAlarmType(cabinet);
			cabinetDAO.updateJYCabinet(cabinet);
		}

	}

	@Override
	public int getTotalCount(String hql,Map<String,Object> parameters) {
		// TODO Auto-generated method stub
		return cabinetDAO.getTotalCount(hql,parameters);
	}

	@Override
	public boolean cabinetIsExist(String cabNumber) {
		// TODO Auto-generated method stub
		List<JYCabinet> list = cabinetDAO.findJYCabinetByHql("from JYCabinet cabinet where cabinet.tag = 1 and cabinet.cabNumber ='"+cabNumber+"'");
		if (list.size()>0){
			return true;
		}
		return false;
	}
	private boolean isGlobalAlarmCollection(JYCabinet cabinet){
		if(cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0
				&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0
				&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0
				&&cabinet.getAlarmTypeCollect().getAlarmType4().getEnable()==0)
			return true;
		else
			return false;
			
	}
	private JYAlarmTypeCollect createAlarmCollection(JYCabinet cabinet){
		JYAlarmTypeCollect alarmTypeCollect = cabinet.getAlarmTypeCollect();
		alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getDateStr(new Date(),"yyyyMMddHHmmss"));
		JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
		JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
		JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
		JYAlarmType alarmType4 = alarmTypeCollect.getAlarmType4();
		alarmType1.setId(alarmTypeCollect.getId()+1000);
		alarmType1.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE1HQL).get(0));
		alarmType2.setId(alarmTypeCollect.getId()+1001);
		alarmType2.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE2HQL).get(0));
		alarmType3.setId(alarmTypeCollect.getId()+1002);
		alarmType3.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE3HQL).get(0));
		alarmType4.setId(alarmTypeCollect.getId()+1003);
		alarmType4.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE4HQL).get(0));
		this.alarmTypeService.saveJYAlarmType(alarmType1);
		this.alarmTypeService.saveJYAlarmType(alarmType2);
		this.alarmTypeService.saveJYAlarmType(alarmType3);
		this.alarmTypeService.saveJYAlarmType(alarmType4);	
		this.alarmTypeCollectService.saveJYAlarmTypeCollect(alarmTypeCollect);
		return alarmTypeCollect;
	}
	private void removedAlarmCollection(JYCabinet cabinet){
		JYAlarmTypeCollect collect = cabinet.getAlarmTypeCollect();
		JYAlarmType type1 = collect.getAlarmType1();
		JYAlarmType type2 = collect.getAlarmType2();
		JYAlarmType type3 = collect.getAlarmType3();
		JYAlarmType type4 = collect.getAlarmType4();

		this.alarmTypeCollectService.removeJYAlarmTypeCollect(collect);
		this.alarmTypeService.removeJYAlarmType(type1);
		this.alarmTypeService.removeJYAlarmType(type2);
		this.alarmTypeService.removeJYAlarmType(type3);
		this.alarmTypeService.removeJYAlarmType(type4);
	}
	private void saveAlarmType(JYCabinet cabinet){
		JYAlarmTypeCollect alarmTypeCollect = cabinet.getAlarmTypeCollect();
		JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
		alarmType1.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE1HQL).get(0));
		JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
		alarmType2.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE2HQL).get(0));
		JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
		alarmType3.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE3HQL).get(0));
		JYAlarmType alarmType4 = alarmTypeCollect.getAlarmType4();
		alarmType4.setType(this.constantService.findJYConstantByHql(Constant.ALARMTYPE4HQL).get(0));
		this.alarmTypeService.updateJYAlarmType(alarmType1);
		this.alarmTypeService.updateJYAlarmType(alarmType2);
		this.alarmTypeService.updateJYAlarmType(alarmType3);
		this.alarmTypeService.updateJYAlarmType(alarmType4);	
	}
}
