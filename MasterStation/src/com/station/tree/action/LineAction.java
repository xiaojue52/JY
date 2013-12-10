package com.station.tree.action;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
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
        Constant.flush(dataMap);
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
