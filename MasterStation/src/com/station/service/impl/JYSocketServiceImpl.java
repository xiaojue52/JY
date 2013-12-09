package com.station.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.station.dao.JYAlarmDAO;
import com.station.dao.JYCabinetDAO;
import com.station.dao.JYCabinetHistoryDAO;
import com.station.dao.JYDetectorDAO;
import com.station.dao.JYDeviceDAO;
import com.station.dao.JYHistoryDAO;
import com.station.po.JYAlarm;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYCabinet;
import com.station.po.JYCabinetHistory;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.po.JYHistory;
import com.station.service.JYSocketService;

public class JYSocketServiceImpl implements JYSocketService {
	private JYDetectorDAO detectorDAO;
	private JYHistoryDAO historyDAO;
	private JYCabinetHistoryDAO cabinetHistoryDAO;
	private JYAlarmDAO alarmDAO;
	private JYDeviceDAO deviceDAO;
	private JYCabinetDAO cabinetDAO;
	
	
	public void setCabinetDAO(JYCabinetDAO cabinetDAO) {
		this.cabinetDAO = cabinetDAO;
	}

	public void setDeviceDAO(JYDeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	public void setAlarmDAO(JYAlarmDAO alarmDAO) {
		this.alarmDAO = alarmDAO;
	}

	public void setCabinetHistoryDAO(JYCabinetHistoryDAO cabinetHistoryDAO) {
		this.cabinetHistoryDAO = cabinetHistoryDAO;
	}

	public void setDetectorDAO(JYDetectorDAO detectorDAO) {
		this.detectorDAO = detectorDAO;
	}

	public void setHistoryDAO(JYHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	//private JYHistory history;
	@Override
	public void saveDate(String cabNumber,Map<Integer, List<Float>> map,String createDate) {
		// TODO Auto-generated method stub
		JYCabinetHistory cabinetHistory = new JYCabinetHistory();
		//String hqlC = "from JYCabinet cabinet where tag = 1 and cabinet.cabNumber = '"+cabNumber+"'";
		String h = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
		cabNumber+"' and detector.device.tag = 1 and detector.device.cabinet.tag = 1";
		List<JYDetector> listH = this.detectorDAO.findJYDetectorByHql(h);
		
		SimpleDateFormat  df= new SimpleDateFormat("yyyyMMddHHmmss");
        java.util.Date date = null;
		try {
			date = df.parse(createDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}//
		
		if (listH.size()==0)return;
		cabinetHistory.setId(String.valueOf(System.nanoTime()));
		cabinetHistory.setCabinet(listH.get(0).getDevice().getCabinet());
		cabinetHistory.setDate(date);
		cabinetHistoryDAO.saveJYCabinetHistory(cabinetHistory);
		Iterator<Map.Entry<Integer, List<Float>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, List<Float>> mEntry = (Map.Entry<Integer, List<Float>>) iter
					.next();
			List<Float> valueList = (List<Float>) mEntry.getValue();
			Integer positionNumber = (Integer)mEntry.getKey();
			String hql = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
			cabNumber+"' and detector.device.tag = 1" +
				" and detector.device.positionNumber = "+positionNumber+" order by to_number(replace(detector.detectorId,'Detector',''))";
			List<JYDetector> list = this.detectorDAO.findJYDetectorByHql(hql);
			if (list.size()==0&&list.size()!=4)continue;
			this.checkAlarm(valueList,list,date);
			for (int i=0;i<valueList.size();i++){
				//float value = valueList.get(i);				
				JYHistory history = new JYHistory();
				history.setDate(date);
				history.setDetector(list.get(i));
				history.setValue(valueList.get(i));
				history.setCabinetHistory(cabinetHistory);
				historyDAO.saveJYHistory(history);
				list.get(i).setHistory(history);
				detectorDAO.updateJYDetector(list.get(i));
			}
		}
	}
	public void checkAlarm(List<Float> valueList,List<JYDetector> list,Date date){
		//return null;
		float a = valueList.get(0);
		float b = valueList.get(1);
		float c = valueList.get(2);
		float d = valueList.get(3);
		JYDevice device = list.get(0).getDevice();
		JYAlarmTypeCollect collect = device.getCabinet().getAlarmTypeCollect();
		JYAlarmType type1 = collect.getAlarmType1();
		JYAlarmType type2 = collect.getAlarmType2();
		JYAlarmType type3 = collect.getAlarmType3();
		JYAlarm alarm = new JYAlarm();
		alarm.setDate(date);
		String alarmText = "";
		if (type1.getEnable()==1){
			String temp = "";
			for (int i=0;i<3;i++){
				if (valueList.get(i)>type1.getValue()){
					temp = temp + list.get(i).getName();
				}
			}
			if (temp.length()>0)
				alarmText = temp+"温度高于设定值("+type1.getValue()+"℃)<br>";
		}
		if (type2.getEnable()==1){
			if (Math.abs(a-b)>type2.getValue()||Math.abs(a-c)>type2.getValue()||Math.abs(b-c)>type3.getValue()){
				alarmText = alarmText + "三相之间温差超出设定值("+type2.getValue()+"℃)<br>";
			}
		}
		if (type3.getEnable()==1){
			if (Math.abs(a-d)>type3.getValue()||Math.abs(b-d)>type3.getValue()||Math.abs(c-d)>type3.getValue()){
				alarmText = alarmText + "三相与环境温差超出设定值("+type3.getValue()+"℃)";
			}
		}
		if (alarmText.length()>0){
			alarm.setIsCabinet("0");
			alarm.setDevice(device);
			alarm.setStatus("0");
			alarm.setAlarmText(alarmText);
			alarm.setId(String.valueOf(System.nanoTime()));
			
			this.alarmDAO.saveJYAlarm(alarm);
			device.setAlarm(alarm);
			this.deviceDAO.updateJYDevice(device);
		}
	}

	@Override
	public void updateCabinetStatus(String cabNumber) {
		// TODO Auto-generated method stub
		List<JYCabinet> list = cabinetDAO.findJYCabinetByHql("from JYCabinet cabinet where cabinet.tag = 1 and cabinet.cabNumber ='"+cabNumber+"'");
		if (list.size()>0){
			Date date = new Date();
			list.get(0).setLoginTime(date);
			list.get(0).setStatus(1);
		}
	}

	@Override
	public void saveAlarm(String cabNumber, int type, Date date,
			String content) {
		// TODO Auto-generated method stub
		JYAlarm alarm = new JYAlarm();
		List<JYDevice> list = deviceDAO.findJYDeviceByHql("from JYDevice device where device.tag = 1 and device.cabinet.tag = 1 and device.cabinet.cabNumber ='"+cabNumber+"'");
		if (list.size()>0){
			alarm.setAlarmText(content);
			alarm.setDate(date);
			alarm.setId(String.valueOf(System.nanoTime()));
			alarm.setIsCabinet("1");
			alarm.setStatus("0");
			list.get(0).getCabinet().setAlarm(alarm);
			alarm.setDevice(list.get(0));
			this.alarmDAO.saveJYAlarm(alarm);
			this.cabinetDAO.updateJYCabinet(list.get(0).getCabinet());		
		}
		
	}

}
