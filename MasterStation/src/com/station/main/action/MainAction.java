package com.station.main.action;


import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.LoginStatus;
import com.station.pagebean.PageBean;
import com.station.query.column.SqlQueryColumn;
import com.station.service.DeviceService;
import com.station.socket.SocketListener;

@SuppressWarnings("serial")
public class MainAction extends ActionSupport {
	private DeviceService deviceService;
	private int methodCode = 0;
	private PageBean pageBean;
	private int page = 1;
	private SqlQueryColumn sqlDeviceColumn;
	private static SocketListener socketListener;
    private int timer = 3000;
    
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

	public DeviceService getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public String getCurrent() throws Exception{
		
		this.methodCode = 0;
		final String hql = this.createSql();
		this.pageBean = deviceService.getPerPage(9, page, hql);
		page = 1;
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
		this.pageBean = deviceService.getPerPage(9, page, hql);
		page = 1;
		HttpServletRequest request = ServletActionContext.getRequest ();
		request.setAttribute("methodCode",this.methodCode );
		
		return "success";
	}

	public String createSql(){
		String hql;
		String query = null;
		
		if (sqlDeviceColumn!=null)	
			query = sqlDeviceColumn.getQueryString();
			
		if(LoginStatus.checkUserAccess()==1){
			if (query==null)
				hql="from Device device";
			else
				hql="from Device device "+query;
		}else if (query==null)
			hql="from Device device where owner = '"+LoginStatus.getUsername()+"' or owner='-'";
		else
			hql="from Device device "+query +" and owner = '"+LoginStatus.getUsername()+"' or owner='-'";
		sqlDeviceColumn = null;
		return hql;
	}
	
}
