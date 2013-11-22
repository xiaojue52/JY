package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYLine;
import com.station.service.JYLineService;


@SuppressWarnings("serial")
public class LineAction extends ActionSupport{
	private JYLineService lineService;
	private JYLine line;

	public JYLine getLine() {
		return line;
	}
	public void setLine(JYLine line) {
		this.line = line;
	}

	public void setLineService(JYLineService lineService) {
		this.lineService = lineService;
	}
	public void showLineRecord(){
		line = lineService.findLineById(line.getLineId());
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("line", line);
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
	public String addLineAction(){
		lineService.saveLine(line);
		return SUCCESS;
	}
	public String deleteLineAction(){
		line = lineService.findLineById(line.getLineId());
		lineService.removeLine(line);
		return SUCCESS;
	}
}
