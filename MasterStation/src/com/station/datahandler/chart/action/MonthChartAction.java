package com.station.datahandler.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.pagebean.PageBean;
import com.station.po.JYHistoryMonthChartData;
import com.station.service.JYHistoryMonthChartDataService;

@SuppressWarnings("serial")
public class MonthChartAction extends ActionSupport {
	private JYHistoryMonthChartDataService historyMonthChartDataService;
	private PageBean pageBean;
	private int page = 1;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	private String queryStartDate;
	private String queryEndDate;
	private String queryStartOtherDate;
	private String queryEndOtherDate;
	private String queryDeviceId;
	private String queryDetector;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

	public String getQueryStartOtherDate() {
		return queryStartOtherDate;
	}

	public void setQueryStartOtherDate(String queryStartOtherDate) {
		this.queryStartOtherDate = queryStartOtherDate;
	}

	public String getQueryEndOtherDate() {
		return queryEndOtherDate;
	}

	public void setQueryEndOtherDate(String queryEndOtherDate) {
		this.queryEndOtherDate = queryEndOtherDate;
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

	public String getQueryDeviceId() {
		return queryDeviceId;
	}

	public void setQueryDeviceId(String queryDevice) {
		this.queryDeviceId = queryDevice;
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

	public void setHistoryMonthChartDataService(
			JYHistoryMonthChartDataService historyMonthChartDataService) {
		this.historyMonthChartDataService = historyMonthChartDataService;
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
	class ChartData{
		public String name;
		public List<List<Double>> data;
	}
	private ChartData getChartData(String arg0,int arg1,int arg2){
		ChartData chartData = new ChartData(); 
		final String hql = this.createSql(arg0,arg1,arg2);
		List<JYHistoryMonthChartData> listH = this.historyMonthChartDataService
		.findJYHistoryMonthChartDataByHql(hql);
		List<List<Double>> data = new ArrayList<List<Double>>();
		for (int i = 0; i < listH.size(); i++) {
			List<Double> list = new ArrayList<Double>();
			list.add(Double.valueOf(listH.get(i).getDate().getDate()));
			if (listH.get(i).getValue()!=null)
				list.add(Double.valueOf(listH.get(i).getValue()));
			else
				list.add(null);
			data.add(list);
		}
		String name;
		if (arg1==1){
			name = arg0+"MAX值";
		}else
			name = arg0+"Min值";
		
		if (arg2==0){
			name = queryStartDate.split("-")[1]+"月"+name;
		}
		else
			name = queryStartOtherDate.split("-")[1]+"月"+name;
		chartData.name = name;
		chartData.data = data;
		return chartData;
	}
	public void listHistoryAction() throws Exception {
		List<ChartData> listValue = new ArrayList<ChartData>();
		ChartData dataAMax = getChartData("A相",1,0);
		listValue.add(dataAMax);
		ChartData dataAMin = getChartData("A相",0,0);
		listValue.add(dataAMin);
		
		
		ChartData dataBMax = getChartData("B相",1,0);
		listValue.add(dataBMax);
		ChartData dataBMin = getChartData("B相",0,0);
		listValue.add(dataBMin);
		
		ChartData dataCMax = getChartData("C相",1,0);
		listValue.add(dataCMax);
		ChartData dataCMin = getChartData("C相",0,0);
		listValue.add(dataCMin);
		
		ChartData dataDMax = getChartData("环境",1,0);
		listValue.add(dataDMax);
		ChartData dataDMin = getChartData("环境",0,0);
		listValue.add(dataDMin);
		
		if (!queryStartOtherDate.equals("-1")){
			ChartData dataAOMax = getChartData("A相",1,1);
			listValue.add(dataAOMax);
			ChartData dataAOMin = getChartData("A相",0,1);
			listValue.add(dataAOMin);
			ChartData dataBOMax = getChartData("B相",1,1);
			listValue.add(dataBOMax);
			ChartData dataBOMin = getChartData("B相",0,1);
			listValue.add(dataBOMin);
			
			ChartData dataCOMax = getChartData("C相",1,1);
			listValue.add(dataCOMax);
			ChartData dataCOMin = getChartData("C相",0,1);
			listValue.add(dataCOMin);
			
			ChartData dataDOMax = getChartData("环境",1,1);
			listValue.add(dataDOMax);
			ChartData dataDOMin = getChartData("环境",0,1);
			listValue.add(dataDOMin);
		}
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("data", listValue);
		Constant.flush(dataMap);
	}
    //tag:0-min 1-max
	public String createSql(String detector,int tag,int which) {
		String hql = "from JYHistoryMonthChartData history where ";
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
		if (queryStartOtherDate == null || queryStartOtherDate.length() == 0)
			queryStartOtherDate = "1000-01-01";
		if (queryEndOtherDate == null || queryEndOtherDate.length() == 0)
			queryEndOtherDate = "9999-12-12";
		if (queryDeviceId == null || queryDeviceId.length() == 0)
			queryDeviceId = "%";
		if (queryDetector == null || queryDetector.length() == 0)
			queryDetector = "%";
		String queryStart;
		String queryEnd;
		if (which==0){
			queryStart = queryStartDate;
			queryEnd = queryEndDate;
		}else{
			queryStart = queryStartOtherDate;
			queryEnd = queryEndOtherDate;
		}
			
		hql = hql + "history.detector.device.cabinet.line.name like '%"
				+ queryLine + "%' and "
				+ "history.detector.device.cabinet.cabNumber like '%"
				+ queryNumber + "%' and "
				+ "history.detector.device.id = '" + queryDeviceId
				+ "' and " + "history.detector.name like '%" + queryDetector
				+ "%' and "
				+ "history.detector.device.cabinet.cabType.value like '%"
				+ queryType + "%' and " + "history.date>= TO_DATE('"
				+ queryStart + " 00:00:00"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.date <= TO_DATE('" + queryEnd + " 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.detector.device.cabinet.user.username like '%"
				+ queryUser + "%' and history.detector.name ='" + detector
				+ "' and history.tag ='"+tag+"' ORDER BY history.date";
		return hql;
	}

}
