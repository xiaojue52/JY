package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.station.service.JYCabinetService;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;
import com.station.service.JYLineService;
import com.station.tree.TreeService;

@SuppressWarnings("serial")
public class CreateTreeAction extends ActionSupport {
	private JYLineService lineService;
	private JYCabinetService cabinetService;
	private JYDeviceService deviceService;
	private JYDetectorService detectorService;
	private String id;
	private String level;
	private int tag = 0;
	private String queryLine = "-1";
	private String queryType = "-1";
	private String queryNumber = "-1";
	private String queryUserGroup = "-1";
	private String toExpendLineId = "-1";

	
	public String getToExpendLineId() {
		return toExpendLineId;
	}

	public void setToExpendLineId(String toExpendLineId) {
		this.toExpendLineId = toExpendLineId;
	}

	public String getQueryLine() {
		return queryLine;
	}

	public void setQueryLine(String queryLine) {
		this.queryLine = queryLine;
	}


	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(String queryNumber) {
		this.queryNumber = queryNumber;
	}

	public String getQueryUserGroup() {
		return queryUserGroup;
	}

	public void setQueryUserGroup(String queryUserGroup) {
		this.queryUserGroup = queryUserGroup;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLineService(JYLineService lineService) {
		this.lineService = lineService;
	}

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}

	public void setDeviceService(JYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	public void setDetectorService(JYDetectorService detectorService) {
		this.detectorService = detectorService;
	}

	public void getTreeData() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			String jsonString = "[]";
			TreeService treeService = new TreeService();
			//System.out.print("id:"+id+"..level:"+level+"..tag:"+tag);
			if (level == null || level.length() == 0) {
				jsonString = "[]";
			} else if (level.equals("0")&&tag==0) {
				jsonString = treeService.getLineNodes(lineService,cabinetService,toExpendLineId);
			} else if (level.equals("1")) {
				jsonString = treeService.getCabinetNodes(cabinetService,id);
			} else if (level.equals("2")) {
				jsonString = treeService.getDeviceNodes(deviceService,id);
			} else if (level.equals("3")){
				jsonString = treeService.getDetectorNodes(detectorService,id);
			} else if (level.equals("0")&&tag==1){
				jsonString = treeService.queryCabinet(cabinetService, this.queryLine, this.queryType, this.queryNumber, this.queryUserGroup);
			}
			level = null;
			tag = 0;
			queryLine = "-1";
			queryType = "-1";
			queryNumber = "-1";
			queryUserGroup = "-1";
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String createTreeAction(){
		//System.out.print(this.toExpendLineId);
		return SUCCESS;
	}
}
