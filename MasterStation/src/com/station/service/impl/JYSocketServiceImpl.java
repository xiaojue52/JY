package com.station.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.station.dao.JYCabinetHistoryDAO;
import com.station.dao.JYDetectorDAO;
import com.station.dao.JYHistoryDAO;
import com.station.po.JYCabinetHistory;
import com.station.po.JYDetector;
import com.station.po.JYHistory;
import com.station.service.JYSocketService;

public class JYSocketServiceImpl implements JYSocketService {
	private JYDetectorDAO detectorDAO;
	private JYHistoryDAO historyDAO;
	private JYCabinetHistoryDAO cabinetHistoryDAO;
	
	
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
		SimpleDateFormat  formatTime= new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = new java.sql.Date(formatTime.parse(createDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Time time = null;
		try {
			time = new java.sql.Time(formatTime.parse(createDate).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Iterator<Map.Entry<Integer, List<Float>>> iter = map.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Integer, List<Float>> mEntry = (Map.Entry<Integer, List<Float>>) iter
					.next();
			List<Float> valueList = (List<Float>) mEntry.getValue();
			Integer positionNumber = (Integer)mEntry.getKey();
			
			for (int i=0;i<valueList.size();i++){
				if (i==0){
					String hql = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
								cabNumber+"' and detector.device.tag = 1" +
					" and detector.device.positionNumber = "+positionNumber+" and detector.name='A相'";
					List<JYDetector> list = this.detectorDAO.findJYDetectorByHql(hql);
					if(list.size()>0){
						cabinetHistory.setId(String.valueOf(System.nanoTime()));
						cabinetHistory.setCabinet(list.get(0).getDevice().getCabinet());
						cabinetHistory.setCreateDate(date);
						cabinetHistory.setCreateTime(time);
						cabinetHistoryDAO.saveJYCabinetHistory(cabinetHistory);
						JYHistory history = new JYHistory();
						history.setCreateDate(date);
						history.setCreateTime(time);
						history.setDetector(list.get(0));
						history.setValue(valueList.get(0));
						history.setCabinetHistory(cabinetHistory);
						historyDAO.saveJYHistory(history);
						list.get(0).setHistory(history);
						detectorDAO.updateJYDetector(list.get(0));
					}
				}
				if (i==1){
					String hql = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
					cabNumber+"' and detector.device.tag = 1" +
		" and detector.device.positionNumber = "+positionNumber+" and detector.name='B相'";
					List<JYDetector> list = this.detectorDAO.findJYDetectorByHql(hql);
					if(list.size()>0){
						JYHistory history = new JYHistory();
						history.setCreateDate(date);
						history.setCreateTime(time);
						history.setDetector(list.get(0));
						history.setValue(valueList.get(1));
						historyDAO.saveJYHistory(history);
						list.get(0).setHistory(history);
						detectorDAO.updateJYDetector(list.get(0));
					}
				}
				if (i==2){
					String hql = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
					cabNumber+"' and detector.device.tag = 1" +
		" and detector.device.positionNumber = "+positionNumber+" and detector.name='C相'";
					List<JYDetector> list = this.detectorDAO.findJYDetectorByHql(hql);
					if(list.size()>0){
						JYHistory history = new JYHistory();
						history.setCreateDate(date);
						history.setCreateTime(time);
						history.setDetector(list.get(0));
						history.setValue(valueList.get(2));
						historyDAO.saveJYHistory(history);
						list.get(0).setHistory(history);
						detectorDAO.updateJYDetector(list.get(0));
					}
				}
				if (i==3){
					String hql = "from JYDetector detector where tag = 1 and detector.device.cabinet.cabNumber='" +
					cabNumber+"' and detector.device.tag = 1" +
		" and detector.device.positionNumber = "+positionNumber+" and detector.name='环境'";
					List<JYDetector> list = this.detectorDAO.findJYDetectorByHql(hql);
					if(list.size()>0){
						JYHistory history = new JYHistory();
						history.setCreateDate(date);
						history.setCreateTime(time);
						history.setDetector(list.get(0));
						history.setValue(valueList.get(3));
						historyDAO.saveJYHistory(history);
						list.get(0).setHistory(history);
						detectorDAO.updateJYDetector(list.get(0));
					}
				}
			}
		}
	}

}
