package com.station.datahandler.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.pagebean.PageBean;
import com.station.po.JYHistoryChartData;
import com.station.service.JYHistoryChartDataService;

@SuppressWarnings("serial")
public class MoreChartAction extends ActionSupport {
	private JYHistoryChartDataService historyChartDataService;
	private PageBean pageBean;
	private int page = 1;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	private String queryStartDate;
	private String queryEndDate;
	private String queryDevice;
	private String queryDetector;
	private String queryStrings;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

	public String getQueryStrings() {
		return queryStrings;
	}

	public void setQueryStrings(String queryStrings) {
		this.queryStrings = queryStrings;
	}

	public List<Integer> getPageNumberList() {
		pageNumberList.clear();
		pageNumberList.add(10);
		pageNumberList.add(20);
		pageNumberList.add(30);
		pageNumberList.add(40);
		return pageNumberList;
	}

	public void setPageNumberList(List<Integer> pageNumberList) {
		this.pageNumberList = pageNumberList;
	}

	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	public String getQueryDevice() {
		return queryDevice;
	}

	public void setQueryDevice(String queryDevice) {
		this.queryDevice = queryDevice;
	}

	public String getQueryDetector() {
		return queryDetector;
	}

	public void setQueryDetector(String queryDetector) {
		this.queryDetector = queryDetector;
	}

	public String getQueryLine() {
		return queryLine;
	}

	public void setQueryLine(String queryLine) {
		this.queryLine = queryLine;
	}

	public String getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(String queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryUser() {
		return queryUser;
	}

	public void setQueryUser(String queryUser) {
		this.queryUser = queryUser;
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

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	class MoreData{
		public String name;
		public List<Float> data;
		public long pointStart;
	}
	public void listHistoryAction() throws Exception {
		String[] listDetector = queryStrings.split(",");
		if (listDetector.length==0||listDetector[0].length()==0)return;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<MoreData> listD = new ArrayList<MoreData>();
		for (int i = 0; i < listDetector.length; i++) {
			final String hql = this.createSql(listDetector[i]);
			List<JYHistoryChartData> list = this.historyChartDataService.findJYHistoryChartDataByHql(hql);
			if(list.size()!=0){
				String str = list.get(0).getDetector().getDevice().getCabinet().getLine().getName()+
				list.get(0).getDetector().getDevice().getCabinet().getCabNumber()+
				list.get(0).getDetector().getDevice().getCabinet().getCabType().getValue()+
				list.get(0).getDetector().getDevice().getName()+
				list.get(0).getDetector().getName();
				List<Float> listValue = new ArrayList<Float>();
				for (int j=0;j<list.size();j++){
					listValue.add(list.get(j).getValue());
				}
				//map.put(str, listValue);
				//String strD = "{name:\""+str+"\",data:"+listValue+"}";
				MoreData moreData = new MoreData();
				if (list.size()>0)
					moreData.pointStart = list.get(0).getDate().getTime()+8*60*60*1000;
				moreData.name = str;
				moreData.data = listValue;
				listD.add(moreData);
			}
			// dataMap.put(list.get(0).getDetector().getDevice().get, value);
		}
		dataMap.put("data", listD);
		Constant.flush(dataMap);
	}

	public String createSql(String detectorId) {
		String hql = "from JYHistoryChartData history where ";
		if (queryLine == null || queryLine.length() == 0)
			queryLine = "%";
		if (queryNumber == null || queryNumber.length() == 0)
			queryNumber = "%";
		if (queryType == null || queryType.length() == 0)
			queryType = "%";
		if (queryUser == null || queryUser.length() == 0)
			queryUser = "%";
		if (queryStartDate == null || queryStartDate.length() == 0)
			queryStartDate = "1000-01-01";
		if (queryEndDate == null || queryEndDate.length() == 0)
			queryEndDate = "9999-12-12";
		if (queryDevice == null || queryDevice.length() == 0)
			queryDevice = "%";
		if (queryDetector == null || queryDetector.length() == 0)
			queryDetector = "%";
		hql = hql + "history.detector.device.cabinet.line.name like '%"
				+ queryLine + "%' and "
				+ "history.detector.device.cabinet.cabNumber like '%"
				+ queryNumber + "%' and "
				+ "history.detector.device.name like '%" + queryDevice
				+ "%' and " + "history.detector.name like '%" + queryDetector
				+ "%' and "
				+ "history.detector.device.cabinet.cabType.value like '%"
				+ queryType + "%' and " + "history.date>= TO_DATE('"
				+ queryStartDate+" 00:00:00" + "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.date <= TO_DATE('" + queryEndDate+" 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.detector.device.cabinet.user.username like '%"
				+ queryUser + "%' and history.detector.detectorId ='"
				+ detectorId+"' order by history.date";
		return hql;
	}

}
