package com.station.service.impl;

import java.util.Date;
import java.util.List;

import com.station.dao.JYDetectorDAO;
import com.station.dao.JYHistoryChartDataDAO;
import com.station.dao.JYHistoryMonthChartDataDAO;
import com.station.po.JYDetector;
import com.station.po.JYHistory;
import com.station.po.JYHistoryChartData;
import com.station.po.JYHistoryMonthChartData;
import com.station.service.JYChartDataService;

public class JYChartDataServiceImpl implements JYChartDataService {
	private JYDetectorDAO detectorDAO;
	private JYHistoryChartDataDAO historyChartDataDAO;
	private JYHistoryMonthChartDataDAO historyMonthChartDataDAO;
	
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
			JYHistoryChartData arg0 = new JYHistoryChartData();
			arg0.setDate(date);
			arg0.setDetector(list.get(i));
			arg0.setValue(history.getValue());
			historyChartDataDAO.saveJYHistoryChartData(arg0);
		}
		
	}

	@Override
	public void saveHistoryMonthChartData() {
		// TODO Auto-generated method stub
		String hql = "from JYDetector detector where tag = 1";
		List<JYDetector> list = detectorDAO.findJYDetectorByHql(hql);
		for (int i=0;i<list.size();i++){
			String hql1 = "from JYHistoryChartData history where history.detector.detectorId = '"+list.get(i).getDetectorId()+"' order by history.value";
			List<JYHistoryChartData> historyList = this.historyChartDataDAO.findJYHistoryChartDataByHql(hql1);
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

}
