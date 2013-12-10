package com.station.tree.action;

import java.util.HashMap;
import java.util.Map;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
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
		detector.getDevice().setAlarm(null);
		detector.getDevice().getCabinet().setAlarm(null);
		detector.setHistory(null);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("detector", detector);
        Constant.flush(dataMap);
	}

}
