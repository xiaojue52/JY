package com.station.datahandler.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYHistoryChartData;
import com.station.service.JYHistoryChartDataService;

@SuppressWarnings("serial")
public class MoreChartAction extends ActionSupport {
	private JYHistoryChartDataService historyChartDataService;
	private String queryStartDate;
	private String queryEndDate;
	private String queryStrings;

	public String getQueryStrings() {
		return queryStrings;
	}

	public void setQueryStrings(String queryStrings) {
		this.queryStrings = queryStrings;
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

	public void setHistoryChartDataService(
			JYHistoryChartDataService historyChartDataService) {
		this.historyChartDataService = historyChartDataService;
	}
	
	class MoreData{
		public String name;
		public List<List<Object>> data;
	}
	public void listHistoryAction() throws Exception {
		String[] listDetector = queryStrings.split(",");
		if (listDetector.length==0||listDetector[0].length()==0)return;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<MoreData> listMoreData = new ArrayList<MoreData>();
		for (int i = 0; i < listDetector.length; i++) {
			final String hql = this.createSql(listDetector[i]);
			List<JYHistoryChartData> list = this.historyChartDataService.findJYHistoryChartDataByHql(hql);
			if(list.size()!=0){
				String str = list.get(0).getDetector().getDevice().getCabinet().getLine().getName()+
				list.get(0).getDetector().getDevice().getCabinet().getCabNumber()+
				list.get(0).getDetector().getDevice().getCabinet().getCabType().getValue()+
				list.get(0).getDetector().getDevice().getName()+
				list.get(0).getDetector().getName();
				List<List<Object>> listValue = new ArrayList<List<Object>>();
				for (int j=0;j<list.size();j++){
					List<Object> value = new ArrayList<Object>();
					value.add(list.get(j).getDate().getTime()+8*60*60*1000);
					value.add(list.get(j).getValue());					
					listValue.add(value);
				}

				MoreData moreData = new MoreData();
				moreData.name = str;
				moreData.data = listValue;
				listMoreData.add(moreData);
			}
		}
		dataMap.put("data", listMoreData);
		Constant.flush(dataMap);
	}

	public String createSql(String detectorId) {
		String hql = "from JYHistoryChartData history where ";

		if (queryStartDate == null || queryStartDate.length() == 0)
			queryStartDate = "1000-01-01";
		if (queryEndDate == null || queryEndDate.length() == 0)
			queryEndDate = "9999-12-12";

		hql = hql 
				+ "history.date>= TO_DATE('"
				+ queryStartDate+" 00:00:00" + "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.date <= TO_DATE('" + queryEndDate+" 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+"history.detector.detectorId ='"
				+ detectorId+"' order by history.date";
		return hql;
	}

}
