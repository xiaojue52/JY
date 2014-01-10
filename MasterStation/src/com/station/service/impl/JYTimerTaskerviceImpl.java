package com.station.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import com.station.constant.Constant;
import com.station.dao.JYAlarmDAO;
import com.station.dao.JYDetectorDAO;
import com.station.dao.JYDeviceDAO;
import com.station.dao.JYHistoryChartDataDAO;
import com.station.dao.JYHistoryDAO;
import com.station.dao.JYHistoryMonthChartDataDAO;
import com.station.po.JYAlarm;
import com.station.po.JYAlarmType;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.po.JYHistory;
import com.station.po.JYHistoryChartData;
import com.station.po.JYHistoryMonthChartData;
import com.station.service.JYTimerTaskService;

public class JYTimerTaskerviceImpl implements JYTimerTaskService {
	private JYDetectorDAO detectorDAO;
	private JYHistoryChartDataDAO historyChartDataDAO;
	private JYHistoryMonthChartDataDAO historyMonthChartDataDAO;
	private JYDeviceDAO deviceDAO;
	private JYHistoryDAO historyDAO;
	private JYAlarmDAO alarmDAO;
	
	public void setAlarmDAO(JYAlarmDAO alarmDAO) {
		this.alarmDAO = alarmDAO;
	}

	public void setHistoryDAO(JYHistoryDAO historyDAO) {
		this.historyDAO = historyDAO;
	}

	public void setDeviceDAO(JYDeviceDAO deviceDAO) {
		this.deviceDAO = deviceDAO;
	}

	public void setHistoryChartDataDAO(JYHistoryChartDataDAO historyChartDataDAO) {
		this.historyChartDataDAO = historyChartDataDAO;
	}

	public void setHistoryMonthChartDataDAO(
			JYHistoryMonthChartDataDAO historyMonthChartDataDAO) {
		this.historyMonthChartDataDAO = historyMonthChartDataDAO;
	}

	public void setDetectorDAO(JYDetectorDAO detectorDAO) {
		this.detectorDAO = detectorDAO;
	}

	@Override
	public void saveHistoryChartData() {
		// TODO Auto-generated method stub
		Date date = new Date();
		String hql = "from JYDetector detector where tag = 1";
		List<JYDetector> list = detectorDAO.findJYDetectorByHql(hql);
		for (int i=0;i<list.size();i++){
			JYHistory history = list.get(i).getHistory();
			if (history==null){
				history = new JYHistory();
			}
			//history.setDate(date);
			JYHistoryChartData historyChartData = new JYHistoryChartData();
			historyChartData.setDate(date);
			historyChartData.setDetector(list.get(i));
			historyChartData.setValue(history.getValue());
			historyChartDataDAO.saveJYHistoryChartData(historyChartData);
		}
		
	}

	@Override
	public void saveHistoryMonthChartData() {
		// TODO Auto-generated method stub
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, day-1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(calendar.getTime());
		String hql = "from JYDetector detector where tag = 1";
		List<JYDetector> list = detectorDAO.findJYDetectorByHql(hql);
		for (int i=0;i<list.size();i++){
			String hql1 = "from historyDAO history where history.detector.detectorId = '"+list.get(i).getDetectorId()+"' and history.date>=to_date('"+dateStr+" 00:00:00','YYYY-MM-DD HH24:mi:ss') and history.date<=to_date('"+dateStr+" 23:59:59','YYYY-MM-DD HH24:mi:ss') order by history.value";
			List<JYHistory> historyList = this.historyDAO.findJYHistoryByHql(hql1);
			if (historyList.size()>0){
				int length = historyList.size();
				JYHistoryMonthChartData max = new JYHistoryMonthChartData();
				max.setDate(historyList.get(length-1).getDate());
				max.setValue(historyList.get(length-1).getValue());
				max.setDetector(historyList.get(length-1).getDetector());
				max.setTag(1);
				this.historyMonthChartDataDAO.saveJYHistoryMonthChartData(max);
				JYHistoryMonthChartData min = new JYHistoryMonthChartData();
				min.setDate(historyList.get(0).getDate());
				min.setValue(historyList.get(0).getValue());
				min.setDetector(historyList.get(0).getDetector());
				min.setTag(0);
				this.historyMonthChartDataDAO.saveJYHistoryMonthChartData(min);
			}
		}
	}

	@Override
	public void saveCheckedTempAlarm() {
		// TODO Auto-generated method stub
		String hql = "from JYDevice device where device.tag = '1' and device.cabinet.tag = '1' and device.cabinet.status = '1'";
		List<JYDevice> list = this.deviceDAO.findJYDeviceByHql(hql);
		for (int i=0;i<list.size();i++){
			if (list.get(0).getCabinet().getAlarmTypeCollect().getAlarmType4().getEnable()==0)return;
			String hql0 = "from JYDetector detector where detector.device.deviceId = '"+list.get(i).getDeviceId()+"'";
			List<JYDetector> detectorList = this.detectorDAO.findJYDetectorByHql(hql0);
			this.figureTemp(detectorList);
		}
		//System.out.print("this is the check temp task method\n");
	}
	private void figureTemp(List<JYDetector> list){
		String alarmText = "";
		if(list==null||list.size()==0)return;
		
		JYAlarmType alarmType = list.get(0).getDevice().getCabinet().getAlarmTypeCollect().getAlarmType4();
		JYDevice device = list.get(0).getDevice();
		
		Float value = alarmType.getValue();
		Integer subValue = alarmType.getSubValue();
		
		Date end = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int currentM = calendar.get(Calendar.MINUTE);
		calendar.set(Calendar.MINUTE, currentM-subValue);
		Date start = calendar.getTime();
		
		String endStr = Constant.getDateStr(end,"yyyy-MM-dd HH:mm:ss");
		String startStr = Constant.getDateStr(start,"yyyy-MM-dd HH:mm:ss");
		for(int i=0;i<list.size();i++){
			
			
			JYDetector detector = list.get(i);
			//String text = alarmType.getType().ge
			String hql = "from JYHistory history where history.detector.detectorId = '"+detector.getDetectorId()+"' and history.date >= TO_DATE('"+startStr+"','YYYY-MM-DD HH24:mi:ss') and history.date <= TO_DATE('"+endStr+"','YYYY-MM-DD HH24:mi:ss') order by history.value";
			List<JYHistory> historyList = this.historyDAO.findJYHistoryByHql(hql);
			if (historyList.size()>2){
				Float min = historyList.get(0).getValue();
				Float max = historyList.get(historyList.size()-1).getValue();
				if(Math.abs(max-min)>subValue){
					//TODO
					alarmText = detector.getName()+":"+subValue+"分钟内温度变化超过设定值（"+value+"℃）<br>";
					//alarm.setAlarmText(subValue+"分钟内温度超过设定值（"+value+"℃）!");
				}
			}
		}
		if(alarmText.length()==0)return;
		
		String hql = "from JYAlarm alarm where alarm.type = '"+JYAlarm.TEMPCHANGTOFAST+"' and alarm.status = '0' and alarm.isCabinet = '0' and alarm.device.deviceId = '"+device.getDeviceId()+"' order by alarm.date desc";
		List<JYAlarm> alarmList = this.alarmDAO.findJYAlarmByHql(hql);
		if (alarmList.size()>0&&alarmList.get(0).getAlarmText().equals(alarmText)&&device.getAlarm().getAlarmText().equals(alarmText)){
			alarmList.get(0).setTimes(alarmList.get(0).getTimes()+1);
			this.alarmDAO.updateJYAlarm(alarmList.get(0));
		}
		else{
			JYAlarm alarm = new JYAlarm();
			alarm.setDate(new Date());
			alarm.setDevice(device);
			alarm.setIsCabinet("0");
			alarm.setStatus("0");
			alarm.setTimes(1);
			alarm.setType(String.valueOf(JYAlarm.TEMPCHANGTOFAST));
			alarm.setAlarmText(alarmText);
			alarm.setId(String.valueOf(System.nanoTime()));
			this.alarmDAO.saveJYAlarm(alarm);
			device.setAlarm(alarm);
			this.deviceDAO.updateJYDevice(device);
		}
	}

	@Override
	public void removeCabinetStatusAlarmAtFixedRate() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int month = calendar.get(Calendar.MONTH);		
		calendar.set(Calendar.MONTH, month - 3);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE,0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND,0);
		Date date = calendar.getTime();
		String startStr = Constant.getDateStr(date,"yyyy-MM-dd HH:mm:ss");
		String hql = "delete from jy_alarm j where j.c_date < to_date('"+startStr+"','YYYY-MM-DD HH24:mi:ss') and (j.id) not in (select d.alarm_id from jy_device d) and (j.id) not in (select c.alarm_id from jy_cabinet c)";
		this.alarmDAO.removeMultiplAlarms(hql);
	}
}
