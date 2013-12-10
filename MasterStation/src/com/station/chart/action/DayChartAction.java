package com.station.chart.action;

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
public class DayChartAction extends ActionSupport {
	private JYHistoryChartDataService historyChartDataService;
	private PageBean pageBean;
	private int page = 1;
	private String queryLine;
	private String queryNumber;
	private String queryType;
	private String queryUser;
	private String queryStartDate;
	private String queryEndDate;
	private String queryDeviceId;
	private String queryDetector;
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

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

	public void setQueryDeviceId(String queryDeviceId) {
		this.queryDeviceId = queryDeviceId;
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

	public void listHistoryAction() throws Exception {
		final String hqlA = this.createSql("A相");
		final String hqlB = this.createSql("B相");
		final String hqlC = this.createSql("C相");
		final String hqlD = this.createSql("环境");
		// this.pageBean = historyChartDataService.getPerPage(pageList, page,
		// hql);
		List<JYHistoryChartData> listHA = this.historyChartDataService
				.findJYHistoryChartDataByHql(hqlA);
		List<JYHistoryChartData> listHB = this.historyChartDataService
				.findJYHistoryChartDataByHql(hqlB);
		List<JYHistoryChartData> listHC = this.historyChartDataService
				.findJYHistoryChartDataByHql(hqlC);
		List<JYHistoryChartData> listHD = this.historyChartDataService
				.findJYHistoryChartDataByHql(hqlD);
		List<Float> listA = new ArrayList<Float>();
		List<Float> listB = new ArrayList<Float>();
		List<Float> listC = new ArrayList<Float>();
		List<Float> listD = new ArrayList<Float>();
		for (int i = 0; i < listHA.size(); i++) {
			listA.add(listHA.get(i).getValue());
		}
		for (int i = 0; i < listHB.size(); i++) {
			listB.add(listHB.get(i).getValue());
		}
		for (int i = 0; i < listHC.size(); i++) {
			listC.add(listHC.get(i).getValue());
		}
		for (int i = 0; i < listHD.size(); i++) {
			listD.add(listHD.get(i).getValue());
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("A", listA);
		dataMap.put("B", listB);
		dataMap.put("C", listC);
		dataMap.put("D", listD);
		Constant.flush(dataMap);
	}

	public String createSql(String detector) {
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
		if (queryDeviceId == null || queryDeviceId.length() == 0)
			queryDeviceId = "%";
		if (queryDetector == null || queryDetector.length() == 0)
			queryDetector = "%";
		hql = hql + "history.detector.device.cabinet.line.name like '%"
				+ queryLine + "%' and "
				+ "history.detector.device.cabinet.cabNumber like '%"
				+ queryNumber + "%' and "
				+ "history.detector.device.id like '%" + queryDeviceId
				+ "%' and " + "history.detector.name like '%" + queryDetector
				+ "%' and "
				+ "history.detector.device.cabinet.cabType.value like '%"
				+ queryType + "%' and " + "history.date>= TO_DATE('"
				+ queryStartDate + " 00:00:00"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.date <= TO_DATE('" + queryEndDate + " 23:59:59"
				+ "','YYYY-MM-DD HH24:mi:ss') and "
				+ "history.detector.device.cabinet.user.username like '%"
				+ queryUser + "%' and history.detector.name = '" + detector
				+ "'";
		return hql;
	}

}
