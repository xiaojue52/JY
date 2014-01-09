package com.station.datahandler.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYHistory;
import com.station.service.JYHistoryService;

@SuppressWarnings("serial")
public class DayChartAction extends ActionSupport {
	private JYHistoryService historyService;
	private String queryStartDate;
	private String queryEndDate;
	private String queryDeviceId;

	public String getQueryDeviceId() {
		return queryDeviceId;
	}

	public void setQueryDeviceId(String queryDeviceId) {
		this.queryDeviceId = queryDeviceId;
	}

	public String getQueryStartDate() {
		return queryStartDate;
	}

	public void setQueryStartDate(String queryStartDate) {
		this.queryStartDate = queryStartDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public void setHistoryService(JYHistoryService historyService) {
		this.historyService = historyService;
	}

	class ChartData{
		public String name;
		public List<List<Object>> data;
	}
	public void listHistoryAction() throws Exception {
		List<ChartData> listValue = new ArrayList<ChartData>();
		ChartData dataA = getChartData("A相");
		ChartData dataB = getChartData("B相");
		ChartData dataC = getChartData("C相");
		ChartData dataD = getChartData("环境");
		listValue.add(dataA);
		listValue.add(dataB);
		listValue.add(dataC);
		listValue.add(dataD);
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", listValue);
		Constant.flush(dataMap);
	}

	private ChartData getChartData(String arg0){
		ChartData chartData = new ChartData(); 
		final String hql = this.createSql(arg0);
		List<JYHistory> listH = this.historyService
		.findJYHistoryByHql(hql);
		List<List<Object>> data = new ArrayList<List<Object>>();
		for (int i = 0; i < listH.size(); i++) {
			List<Object> list = new ArrayList<Object>();
			list.add(listH.get(i).getDate().getTime()+8*60*60*1000);
			list.add(listH.get(i).getValue());
			data.add(list);
		}

		chartData.name = arg0;
		chartData.data = data;
		return chartData;
	}
	
	public String createSql(String detector) {
		String hql = "from JYHistory history where ";
		if (queryStartDate == null || queryStartDate.length() == 0)
			queryStartDate = "1000-01-01";
		if (queryEndDate == null || queryEndDate.length() == 0)
			queryEndDate = "9999-12-12";
		if (queryDeviceId == null || queryDeviceId.length() == 0)
			queryDeviceId = "%";
		hql = hql 
				+ "history.detector.device.id = '" + queryDeviceId
				+ "' and "
				+ "history.date>= TO_DATE('"
				+ queryStartDate + " 00:00:00"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.date <= TO_DATE('" + queryEndDate + " 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.detector.name = '" + detector
				+ "' order by history.date";
		return hql;
	}

}
