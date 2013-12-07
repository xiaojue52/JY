package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYCabinet;
import com.station.service.JYCabinetService;


@SuppressWarnings("serial")
public class CabinetAction extends ActionSupport{
	private JYCabinetService cabinetService;

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
		cabinet.setAlarm(null);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("cabinet", cabinet);
        Date date = cabinet.getCreateTime();
        dataMap.put("dateTime", date.toString());
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
		Date date = new Date();
		cabinet.setCreateTime(date);
		cabinetService.saveJYCabinet(cabinet);
		return SUCCESS;
	}
	public String updateCabinetAction(){
		
		cabinet.setCreateTime(this.cabinetService.findJYCabinetById(this.cabinet.getCabId()).getCreateTime());
		cabinetService.updateJYCabinet(cabinet);
		return SUCCESS;
	}
	public String deleteCabinetAction(){
		cabinet = cabinetService.findJYCabinetById(cabinet.getCabId());
		cabinetService.removeJYCabinet(cabinet);
		return SUCCESS;
	}

}
