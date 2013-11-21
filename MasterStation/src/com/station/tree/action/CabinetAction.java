package com.station.tree.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYAlarmType;
import com.station.po.JYAlarmTypeCollect;
import com.station.po.JYCabinet;
import com.station.service.JYAlarmTypeCollectService;
import com.station.service.JYAlarmTypeService;
import com.station.service.JYCabinetService;
import com.station.service.JYConstantService;
import com.station.service.JYLineService;
import com.station.service.JYUserService;


@SuppressWarnings("serial")
public class CabinetAction extends ActionSupport{
	private JYCabinetService cabinetService;
	private JYLineService lineService;
	private JYUserService userService;
	private JYConstantService constantService;
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmTypeCollectService alarmTypeCollectService;

	public void setAlarmTypeCollectService(
			JYAlarmTypeCollectService alarmTypeCollectService) {
		this.alarmTypeCollectService = alarmTypeCollectService;
	}
	public void setAlarmTypeService(JYAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
	}
	public void setConstantService(JYConstantService constantService) {
		this.constantService = constantService;
	}
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
		if (cabinet.getUser()!=null&&userService.findUserById(cabinet.getUser().getUserId())!=null){
			cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		}
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setPowerLevel(constantService.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantService.findJYConstantById(cabinet.getCabType().getId()));

		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");
		}
		else
		{
			alarmTypeCollect = cabinet.getAlarmTypeCollect();
			alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getCurrentDateStr());
			JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
			JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
			JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
			alarmType1.setId(alarmTypeCollect.getId()+1000);
			alarmType1.setType(this.constantService.findJYConstantByHql(Constant.alarmType1Hql).get(0));
			alarmType2.setId(alarmTypeCollect.getId()+1001);
			alarmType2.setType(this.constantService.findJYConstantByHql(Constant.alarmType2Hql).get(0));
			alarmType3.setId(alarmTypeCollect.getId()+1002);
			alarmType3.setType(this.constantService.findJYConstantByHql(Constant.alarmType3Hql).get(0));
		}
		cabinet.setAlarmTypeCollect(alarmTypeCollect);
		cabinetService.saveJYCabinet(cabinet);
		return SUCCESS;
	}
	public String updateCabinetAction(){
		cabinet.setLine(lineService.findLineById(cabinet.getLine().getLineId()));
		cabinet.setUser(userService.findUserById(cabinet.getUser().getUserId()));
		cabinet.setPowerLevel(constantService.findJYConstantById(cabinet.getPowerLevel().getId()));
		cabinet.setCabType(constantService.findJYConstantById(cabinet.getCabType().getId()));
		JYAlarmTypeCollect alarmTypeCollect = null;
		if (cabinet.getAlarmTypeCollect().getAlarmType1().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType2().getEnable()==0&&cabinet.getAlarmTypeCollect().getAlarmType3().getEnable()==0){
			alarmTypeCollect = this.alarmTypeCollectService.findJYAlarmTypeCollectById("-1");

			JYAlarmTypeCollect collect = cabinet.getAlarmTypeCollect();
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
			cabinetService.updateJYCabinet(cabinet);
			JYAlarmType type1 = collect.getAlarmType1();
			JYAlarmType type2 = collect.getAlarmType2();
			JYAlarmType type3 = collect.getAlarmType3();
			collect.setAlarmType1(null);
			collect.setAlarmType2(null);
			collect.setAlarmType3(null);
			this.alarmTypeCollectService.updateJYAlarmTypeCollect(collect);
			this.alarmTypeCollectService.removeJYAlarmTypeCollect(collect);
			type1.setType(null);
			type2.setType(null);
			type3.setType(null);
			this.alarmTypeService.updateJYAlarmType(type1);
			this.alarmTypeService.updateJYAlarmType(type2);
			this.alarmTypeService.updateJYAlarmType(type3);
			this.alarmTypeService.removeJYAlarmType(type1);
			this.alarmTypeService.removeJYAlarmType(type2);
			this.alarmTypeService.removeJYAlarmType(type3);
			return SUCCESS;
			//cabinet.getAlarmTypeCollect().setAlarmType1(null);
			//cabinet.setAlarmTypeCollect(alarmTypeCollect);
		}else if(cabinet.getAlarmTypeCollect().getId().equals("-1")){
			alarmTypeCollect = new JYAlarmTypeCollect();
			alarmTypeCollect = cabinet.getAlarmTypeCollect();
			alarmTypeCollect.setId(cabinet.getCabNumber()+Constant.getCurrentDateStr());
			JYAlarmType alarmType1 = alarmTypeCollect.getAlarmType1();
			JYAlarmType alarmType2 = alarmTypeCollect.getAlarmType2();
			JYAlarmType alarmType3 = alarmTypeCollect.getAlarmType3();
			alarmType1.setId(alarmTypeCollect.getId()+1000);
			alarmType2.setId(alarmTypeCollect.getId()+1001);
			alarmType3.setId(alarmTypeCollect.getId()+1002);
			cabinet.setAlarmTypeCollect(alarmTypeCollect);
		}
		
		cabinetService.updateJYCabinet(cabinet);
		return SUCCESS;
	}

}
