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
	public void saveDate(String cabId,Map<Integer, List<Float>> map,String createDate) {
		// TODO Auto-generated method stub
		JYCabinetHistory cabinetHistory = new JYCabinetHistory();
		String h = "from JYDetector detector where detector.device.cabinet.cabId='" +
		cabId+"' and detector.device.tag = 1 and detector.device.cabinet.tag = 1";
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
		listH.get(0).getDevice().getCabinet().setDetectTime(date);
		cabinetHistoryDAO.saveJYCabinetHistory(cabinetHistory);
		
		Iterator<Map.Entry<Integer, List<Float>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, List<Float>> mEntry = (Map.Entry<Integer, List<Float>>) iter
					.next();
			List<Float> valueList = (List<Float>) mEntry.getValue();
			Integer positionNumber = (Integer)mEntry.getKey();
			String hql = "from JYDetector detector where detector.device.cabinet.cabId='" +
			cabId+"' and detector.device.tag = 1" +
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
		Float a = valueList.get(0);
		Float b = valueList.get(1);
		Float c = valueList.get(2);
		Float d = valueList.get(3);
		JYDevice device = list.get(0).getDevice();
		
		String hql0 = "from JYAlarm alarm where alarm.device.deviceId = '"+device.getDeviceId()+"' and alarm.isCabinet = '0' and alarm.type = '"+JYAlarm.TERMINALREPEAT+"' and alarm.status = '0' order by alarm.date desc"; 
		
		List<JYAlarm> listAlarms = this.alarmDAO.findJYAlarmByHql(hql0);
		JYAlarm preAlarm = null;
		if (listAlarms!=null&&listAlarms.size()>0){
			preAlarm = listAlarms.get(0);
		}
		if(a==null||b==null||c==null||d==null){
			if (preAlarm!=null&&preAlarm.getType().equals(String.valueOf(JYAlarm.TEMPERROR))){
				preAlarm.setTimes(preAlarm.getTimes()+1);
				this.alarmDAO.updateJYAlarm(preAlarm);
			}
			else{
				JYAlarm alarm = new JYAlarm();
				alarm.setAlarmText("温度无法解析");
				alarm.setType(String.valueOf(JYAlarm.TEMPERROR));
				alarm.setTimes(1);
				alarm.setDate(date);
				alarm.setIsCabinet("0");
				alarm.setId(String.valueOf(System.nanoTime()));
				alarm.setDevice(device);
				alarm.setStatus("0");
				this.alarmDAO.saveJYAlarm(alarm);
				device.setAlarm(alarm);
				this.deviceDAO.updateJYDevice(device);
			}
			return;
		}
		
		
		String preAlarmText = "";
		if (preAlarm!=null){
			preAlarmText = preAlarm.getAlarmText();
		}
		
		
		
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
			if (device.getAlarm()!=null&&device.getAlarm().getAlarmText().equals(alarmText)&&preAlarmText.equals(alarmText)){
				preAlarm.setTimes(preAlarm.getTimes()+1);
				this.alarmDAO.updateJYAlarm(preAlarm);
				//this.deviceDAO.updateJYDevice(device);
			}else
			{
				alarm.setTimes(1);
				alarm.setIsCabinet("0");
				alarm.setDevice(device);
				alarm.setStatus("0");
				alarm.setType(String.valueOf(JYAlarm.TERMINALREPEAT));
				alarm.setAlarmText(alarmText);
				alarm.setId(String.valueOf(System.nanoTime()));
			
				this.alarmDAO.saveJYAlarm(alarm);
				device.setAlarm(alarm);
				this.deviceDAO.updateJYDevice(device);
			}
		}
	}

	/**
	 * 清除报警，设备状态改为在线
	 */
	@Override
	public void updateCabinetStatus(String cabId) {
		// TODO Auto-generated method stub
		JYCabinet cabinet = this.cabinetDAO.findJYCabinetById(cabId);
		Date date = new Date();
		cabinet.setDetectTime(date);
		cabinet.setStatus(1);
		cabinet.setAlarm(null);
		this.cabinetDAO.updateJYCabinet(cabinet);
	}

	/*
	 * (non-Javadoc)
	 * @see com.station.service.JYSocketService#saveAlarm(java.lang.String, int, java.util.Date, java.lang.String)
	 * 柜体故障
	 */
	@Override
	public void saveAlarm(String cabId, int type, Date date,
			String content) {
		// TODO Auto-generated method stub
		
		JYAlarm alarm = new JYAlarm();
		List<JYDevice> list = deviceDAO.findJYDeviceByHql("from JYDevice device where device.tag = 1 and device.cabinet.tag = 1 and device.cabinet.cabId ='"+cabId+"'");
		if (list.size()>0){
			JYCabinet cabinet = list.get(0).getCabinet();
			//if (cabinet.getAlarm()!=null&&type!=2)return;
			JYAlarm preAlarm = cabinet.getAlarm();
			if (preAlarm!=null){
				if (type==JYAlarm.HEARTBEATOFFLINE)return;
				switch (Integer.valueOf(preAlarm.getType())){
				case JYAlarm.DEVICEOFFLINE:
					if (type==JYAlarm.DEVICEOFFLINE)return;
					break;
				case JYAlarm.DEVICEREEOR:
					break;
				default:
					break;
				}
				
			}{
				alarm.setTimes(1);
				alarm.setAlarmText(content);
				alarm.setDate(date);
				alarm.setId(String.valueOf(System.nanoTime()));
				alarm.setIsCabinet("1");
				alarm.setStatus("0");
				alarm.setType(String.valueOf(type));
				cabinet.setAlarm(alarm);
				cabinet.setDetectTime(date);
				alarm.setDevice(list.get(0));
			}
			this.alarmDAO.saveJYAlarm(alarm);
			this.cabinetDAO.updateJYCabinet(cabinet);		
		}	
	}

	@Override
	public JYCabinet getCabinet(String cabId) {
		// TODO Auto-generated method stub
		return this.cabinetDAO.findJYCabinetById(cabId);
	}

	@Override
	public boolean cabinetIsExist(String cabId) {
		// TODO Auto-generated method stub
		List<JYCabinet> list = cabinetDAO.findJYCabinetByHql("from JYCabinet cabinet where cabinet.tag = 1 and cabinet.cabId ='"+cabId+"'");
		if (list.size()>0){
			return true;
		}
		return false;
	}

	@Override
	public List<JYCabinet> findCabinetsByHql(String hql) {
		// TODO Auto-generated method stub
		return cabinetDAO.findJYCabinetByHql(hql);
	}

	@Override
	public String getHistoryDateString(String cabId) {
		// TODO Auto-generated method stub
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String hql = "from JYHistory history where history.detector.device.cabinet.cabId = '"+cabId+"' order by history.date desc";
		List<JYHistory> list = this.historyDAO.findJYHistoryByHql(hql);
		if (list.size()>0)
			return df.format(list.get(0).getDate());
		return null;
	}
	

}
