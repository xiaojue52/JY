package com.station.history.action;


import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.service.JYHistoryService;


@SuppressWarnings("serial")
public class ListHistoryAction extends ActionSupport {
	private JYHistoryService historyService;
	private PageBean pageBean;
	private int page = 1;

	public void setHistoryService(JYHistoryService historyService) {
		this.historyService = historyService;
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
		if (!LoginStatus.isLogined())
			return "unlogin";
		final String hql = "from JYHistory history";
		this.pageBean = historyService.getPerPage(10, page, hql);
		return SUCCESS;
	}
	
	public String createSql(){
		/*String hql;
		String query = null;
		if (sqlDeviceHistoryColumn!=null)	
			query = sqlDeviceHistoryColumn.getQueryString();
			
		if(LoginStatus.checkUserAccess()==1){
			if (query==null)
				hql="from DeviceHistory DeviceHistory" + " ORDER BY id DESC";
			else
				hql="from DeviceHistory DeviceHistory "+query  + " ORDER BY id DESC";
		}else if (query==null)
			hql="from DeviceHistory DeviceHistory where owner = '"+LoginStatus.getUsername()+"'" + " ORDER BY id DESC";
		else
			hql="from DeviceHistory DeviceHistory "+query +" and owner = '"+LoginStatus.getUsername()+"'" + " ORDER BY id DESC";
		sqlDeviceHistoryColumn = null;
		return hql;*/
		return null;
	}
	
}
