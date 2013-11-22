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
			System.out.print(id+"\n");
			if (level == null || level.length() == 0) {
				jsonString = "[]";
			} else if (level.equals("0")) {
				jsonString = treeService.getLineNodes(lineService);
			} else if (level.equals("1")) {
				jsonString = treeService.getCabinetNodes(cabinetService,id);
			} else if (level.equals("2")) {
				jsonString = treeService.getDeviceNodes(deviceService,id);
			} else if (level.equals("3")){
				jsonString = treeService.getDetectorNodes(detectorService,id);
			}
			level = null;
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
