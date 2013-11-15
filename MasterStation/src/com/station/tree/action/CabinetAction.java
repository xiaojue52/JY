package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYCabinet;
import com.station.service.JYCabinetService;
import com.station.service.JYLineService;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class CabinetAction extends ActionSupport{
	private JYCabinetService cabinetService;
	private JYLineService lineService;
	private JYUserService userService;
	
	public void setLineService(JYLineService lineService) {
		this.lineService = lineService;
	}
	public void setUserService(JYUserService userService) {
		this.userService = userService;
	}
	private JYCabinet cabinet;

	public JYCabinet getCabinet() {
		return cabinet;
	}
	public void setCabinet(JYCabinet cabinet) {
		this.cabinet = cabinet;
	}

	public void setCabinetService(JYCabinetService cabinetService) {
		this.cabinetService = cabinetService;
	}
	public void showCabinetRecord(){
		cabinet = cabinetService.findJYCabinetById(cabinet.getCabId());
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("cabinet", cabinet);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			Gson gson = new Gson(); 
			String jsonString = gson.toJson(dataMap); 
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String addCabinetAction(){
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		cabinetService.saveJYCabinet(cabinet);
		return SUCCESS;
	}
	public String updateCabinetAction(){
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		cabinetService.updateJYCabinet(cabinet);
		return SUCCESS;
	}

}
