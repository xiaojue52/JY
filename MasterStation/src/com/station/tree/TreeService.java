package com.station.tree;

import java.util.ArrayList;
import java.util.List;

import com.station.constant.LoginStatus;
import com.station.po.JYCabinet;
import com.station.po.JYDetector;
import com.station.po.JYDevice;
import com.station.po.JYLine;
import com.station.service.JYCabinetService;
import com.station.service.JYDetectorService;
import com.station.service.JYDeviceService;
import com.station.service.JYLineService;

public class TreeService {
	public String getLineNodes(JYLineService lineService,JYCabinetService cabinetService, String toExpendLineId) {
		
		String hql = "from JYLine line where tag = 1 order by to_number(replace(line.lineId,'Line','')) desc";
		List<JYLine> lines = lineService.findAllLineByHql(hql);
		String jsonString = null;
		for (int i=0;i<lines.size();i++){
			String children = "";
			if (toExpendLineId.equals(lines.get(i).getLineId())){
				//children = this.getCabinetNodes(cabinetService, lines.get(i).getLineId());
				children = "expanded:true";
			}
			//System.out.print(children);
			children = "expanded:false";
			if (jsonString ==null){
				jsonString = "{text:'"+lines.get(0).getName()+"',id:'"+lines.get(0).getLineId()+"',level:1,"+children+",icon:'images/line.png'}";
				continue;
			}
			//int j = i+1;
			jsonString = jsonString +",{text:'"+lines.get(i).getName()+"',id:'"+lines.get(i).getLineId()+"',level:1,"+children+",icon:'images/line.png'}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
	}
	public String queryCabinet(JYCabinetService cabinetService, String queryLine, String queryType, String queryNumber, String queryUser){
		if (queryLine.equals("-1"))queryLine = "%"; 
		if (queryType.equals("-1")) queryType = "%"; 
		if (queryNumber.equals("-1")) queryNumber = "%";
		if (queryUser.equals("-1")) queryUser = "%";
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(cabinet.user.username = '"+LoginStatus.getUsername()+"' or cabinet.user.username = '--') and ";
		}
		String hql = "from JYCabinet cabinet where "+temp+" cabinet.line.name like '%"+queryLine+"%' and cabinet.cabType.value like '%"+queryType+"%' and cabinet.cabNumber like '%"+queryNumber+"%' and cabinet.user.username like '%"+queryUser+"%' and tag = 1 order by to_number(replace(cabinet.cabId,'Cab','')) desc";
		List<JYCabinet> list = cabinetService.findJYCabinetByHql(hql);
		

		List<String> listJson = new ArrayList<String>();
		List<JYLine> listLine = new ArrayList<JYLine>();
		while (list.size()>0){
			String lineId = list.get(0).getLine().getLineId();
			listLine.add(list.get(0).getLine());
			String json = null;
			json = "{text:'"+list.get(0).getCabNumber()+list.get(0).getCabType().getValue()+"',id:'"+list.get(0).getCabId()+"',level:2,icon:'images/cabinet.png'}";
			list.remove(0);
			int i=0;
			while (list.size()!=i){
				
				if (lineId.equals(list.get(i).getLine().getLineId())){
					json = json +",{text:'"+list.get(i).getCabNumber()+list.get(i).getCabType().getValue()+"',id:'"+list.get(i).getCabId()+"',level:2,icon:'images/cabinet.png'}";
					list.remove(i);
					i --;
				}
				i ++;
			}
			
			listJson.add(json);
		}
		//System.out.print(jsonString);	
		String jsonString = null;
		for (int i=0;i<listLine.size();i++){
			String children = "["+listJson.get(i)+"]";
			//System.out.print(children);
			if (!children.equals("[null]"))
				children = "children:"+children+",expanded:true";
			else
				children = "children:[]";
			if (jsonString ==null){
				jsonString = "{text:'"+listLine.get(0).getName()+"',id:'"+listLine.get(0).getLineId()+"',level:1,"+children+",icon:'images/line.png'}";
				continue;
			}
			//int j = i+1;
			jsonString = jsonString +",{text:'"+listLine.get(i).getName()+"',id:'"+listLine.get(i).getLineId()+"',level:1,"+children+",icon:'images/line.png'}";
		}
		jsonString = "["+jsonString+"]";
		
		return jsonString;
	}
	public String getCabinetNodes(JYCabinetService cabinetService, String lineId) {
		String temp="1=1 and ";
		if (LoginStatus.checkUserAccess()==2){
			temp = "(cabinet.user.username = '"+LoginStatus.getUsername()+"' or cabinet.user.username = '--') and ";
		}
		String hql = "from JYCabinet cabinet where "+temp+" cabinet.line.lineId = '"+lineId+"' and tag = 1 order by to_number(replace(cabinet.cabId,'Cab','')) desc";
		List<JYCabinet> list = cabinetService.findJYCabinetByHql(hql);
		String jsonString = null;
		for (int i=0;i<list.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+list.get(0).getCabNumber()+list.get(0).getCabType().getValue()+"',id:'"+list.get(0).getCabId()+"',level:2,icon:'images/cabinet.png'}";
				continue;
			}
			jsonString = jsonString +",{text:'"+list.get(i).getCabNumber()+list.get(i).getCabType().getValue()+"',id:'"+list.get(i).getCabId()+"',level:2,icon:'images/cabinet.png'}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
		//return "[{text:\"环网柜1\", id:\"1-1\"},{text:\"环网柜2\", id:\"1-2\", leaf:true},{text:\"环网柜3\", id:\"1-3\", leaf:true},{text:\"环网柜4\", id:\"1-4\", leaf:true},{text:\"环网柜5\", id:\"1-5\", leaf:true}]";
	}

	public String getDeviceNodes(JYDeviceService deviceService, String cabId) {
		String hql = "from JYDevice device where device.cabinet.cabId = '"+cabId+"' and tag = 1 order by to_number(replace(device.deviceId,'Device','')) desc";
		List<JYDevice> list = deviceService.findJYDeviceByHql(hql);
		String jsonString = null;
		for (int i=0;i<list.size();i++){
			if (jsonString ==null){
				jsonString = "{text:'"+list.get(0).getName()+"',id:'"+list.get(0).getDeviceId()+"',level:3,icon:'images/device.png'}";
				continue;
			}
			jsonString = jsonString +",{text:'"+list.get(i).getName()+"',id:'"+list.get(i).getDeviceId()+"',level:3,icon:'images/device.png'}";
		}
		jsonString = "["+jsonString+"]";
		//System.out.print(jsonString);	
		return jsonString;
		//return "[{text:\"长春\", id:\"2-1\", leaf:true},{text:\"吉林\", id:\"2-2\", leaf:true},{text:\"白山\", id:\"2-3\", leaf:true},{text:\"白城\", id:\"2-4\", leaf:true}]";
	}

	public String getDetectorNodes(JYDetectorService detectorService, String deviceId) {
		String hql = "from JYDetector detector where detector.device.deviceId = '"+deviceId+"' and tag = 1 order by to_number(replace(detector.detectorId,'Detector',''))";
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
