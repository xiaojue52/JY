package com.station.tree;

import java.util.List;

import com.station.po.JYCabinet;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.po.JYLine;
import com.station.service.JYCabinetService;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;
import com.station.service.JYLineService;

public class TreeService {
	public String getLineNodes(JYLineService lineService) {
		List<JYLine> lines = lineService.findAllLine();
		String jsonString = null;
		for (int i=0;i<lines.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+lines.get(0).getName()+"',id:'"+lines.get(0).getLineId()+"',level:1}";
				continue;
			}
			//int j = i+1;
			jsonString = jsonString +",{text:'"+lines.get(i).getName()+"',id:'"+lines.get(i).getLineId()+"',level:1}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
	}

	public String getCabinetNodes(JYCabinetService cabinetService, String lineId) {
		String hql = "from JYCabinet cabinet where cabinet.line.lineId = '"+lineId+"'";
		List<JYCabinet> list = cabinetService.findJYCabinetByHql(hql);
		String jsonString = null;
		for (int i=0;i<list.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+list.get(0).getCabNumber()+list.get(0).getCabType()+"',id:'"+list.get(0).getCabId()+"',level:2}";
				continue;
			}
			jsonString = jsonString +",{text:'"+list.get(i).getCabNumber()+list.get(0).getCabType()+"',id:'"+list.get(i).getCabId()+"',level:2}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
		//return "[{text:\"环网柜1\", id:\"1-1\"},{text:\"环网柜2\", id:\"1-2\", leaf:true},{text:\"环网柜3\", id:\"1-3\", leaf:true},{text:\"环网柜4\", id:\"1-4\", leaf:true},{text:\"环网柜5\", id:\"1-5\", leaf:true}]";
	}

	public String getDeviceNodes(JYDeviceService deviceService, String cabId) {
		String hql = "from JYDevice device where device.cabinet.cabId = '"+cabId+"'";
		List<JYDevice> list = deviceService.findJYDeviceByHql(hql);
		String jsonString = null;
		for (int i=0;i<list.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+list.get(0).getName()+"',id:'"+list.get(0).getDeviceId()+"',level:3}";
				continue;
			}
			jsonString = jsonString +",{text:'"+list.get(i).getName()+"',id:'"+list.get(i).getDeviceId()+"',level:3}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
		//return "[{text:\"长春\", id:\"2-1\", leaf:true},{text:\"吉林\", id:\"2-2\", leaf:true},{text:\"白山\", id:\"2-3\", leaf:true},{text:\"白城\", id:\"2-4\", leaf:true}]";
	}

	public String getDetectorNodes(JYDetectorService detectorService, String deviceId) {
		String hql = "from JYDetector detector where detector.device.deviceId = '"+deviceId+"'";
		List<JYDetector> list = detectorService.findJYDetectorByHql(hql);
		String jsonString = null;
		for (int i=0;i<list.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+list.get(0).getName()+"',id:'"+list.get(0).getDetectorId()+"',level:4, leaf:true}";
				continue;
			}
			jsonString = jsonString +",{text:'"+list.get(i).getName()+"',id:'"+list.get(i).getDetectorId()+"',level:4, leaf:true}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
		//return "[{text:\"丰台1区\", id:\"1-1-1\", leaf:true},{text:\"丰台2区\", id:\"1-1-2\", leaf:true},{text:\"丰台3区\", id:\"1-1-3\", leaf:true},{text:\"丰台小学\", id:\"1-1-4\", leaf:true}]";
	}
	
}
