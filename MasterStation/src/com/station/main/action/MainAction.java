package com.station.main.action;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.query.column.SqlQueryColumn;
import com.station.service.JYMonitorService;
import com.station.socket.SocketListener;

@SuppressWarnings("serial")
public class MainAction extends ActionSupport {
	private JYMonitorService monitorService;
	private int methodCode = 0;
	private PageBean pageBean;
	private int page = 1;
	private SqlQueryColumn sqlDeviceColumn;
	private static SocketListener socketListener;
    private int timer = 3000;
    private int pageList = 10;
	private List<Integer> pageNumberList = new ArrayList<Integer>();
	

	public void setPageList(int pageList) {
		this.pageList = pageList;
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
    
	public int getTimer() {
		return timer;
	}
	public void setTimer(int timer) {
		this.timer = timer;
	}
	public static void setSocketListener(SocketListener socketListener) {
		MainAction.socketListener = socketListener;
	}
	public SqlQueryColumn getSqlDeviceColumn() {
		return sqlDeviceColumn;
	}
	public void setSqlDeviceColumn(SqlQueryColumn sqlDeviceColumn) {
		this.sqlDeviceColumn = sqlDeviceColumn;
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

	public void setMonitorService(JYMonitorService monitorService) {
		this.monitorService = monitorService;
	}
	public String getCurrent() throws Exception{
		
		this.methodCode = 0;
		final String hql = "from JYCabinet cabinet";
		this.pageBean = monitorService.getPerPage(pageList, page, hql);
		//page = 1;
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("methodCode",this.methodCode );
		
		return "success";
	}
	public String getDataByTime() throws Exception{
		if (!LoginStatus.isLogined())
			return "unlogin";
		//socketListener.sendCommand();
		//socketListener.sendCommandWithIdentify("1");
		this.methodCode = 1;
		final String hql = this.createSql();
		this.pageBean = monitorService.getPerPage(pageList, page, hql);
		//page = 1;
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("methodCode",this.methodCode );
		
		return "success";
	}

	public String createSql(){
		return null;
	}
	
}
