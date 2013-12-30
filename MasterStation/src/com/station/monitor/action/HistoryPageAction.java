package com.station.monitor.action;

import java.util.ArrayList;
import java.util.List;
import com.opensymphony.xwork2.ActionSupport;
import com.station.pagebean.PageBean;
import com.station.service.JYCabinetHistoryService;

@SuppressWarnings("serial")
public class HistoryPageAction extends ActionSupport {
	private JYCabinetHistoryService cabinetHistoryService;
	private PageBean pageBean;
	private int page = 1;
	private String cabId;
	
	private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();

	public String getCabId() {
		return cabId;
	}

	public void setCabId(String cabId) {
		this.cabId = cabId;
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

	public void setCabinetHistoryService(
			JYCabinetHistoryService cabinetHistoryService) {
		this.cabinetHistoryService = cabinetHistoryService;
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

	public String listHistoryAction() throws Exception {
		final String hql = "from JYCabinetHistory cabinetHistory where cabinetHistory.cabinet.cabId = '"+this.cabId+"' ORDER BY id DESC";
		//final String hql = "select t.collect_time,wm_concat(t.detector_id), wm_concat(d.device_id), wm_concat(de.cab_id)from jy_history t, jy_detector d,jy_device de,jy_cabinet c where d.detector_id = t.detector_id and de.cab_id = c.cab_id and d.device_id = de.device_id group by  t.collect_time";
		this.pageBean = cabinetHistoryService.getPerPage(pageList, page, hql);
		return SUCCESS;
	}
}
