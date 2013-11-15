package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYDetector;
import com.station.service.JYDetectorService;


@SuppressWarnings("serial")
public class DetectorAction extends ActionSupport{
	private JYDetectorService detectorService;
	private JYDetector detector;

	public JYDetector getDetector() {
		return detector;
	}
	public void setDetector(JYDetector detector) {
		this.detector = detector;
	}

	public void setDetectorService(JYDetectorService detectorService) {
		this.detectorService = detectorService;
	}
	public void showDetectorRecord(){
		detector = detectorService.findJYDetectorById(detector.getDetectorId());
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("detector", detector);
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

}
