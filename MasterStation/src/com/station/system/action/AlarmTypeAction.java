/**
 * 设置报警条件类
 */
package com.station.system.action;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.station.constant.Constant;
import com.station.po.JYAlarmType;
import com.station.service.JYAlarmTypeService;

@SuppressWarnings("serial")
public class AlarmTypeAction extends ActionSupport{
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmType alarmType1;
	private JYAlarmType alarmType2;
	private JYAlarmType alarmType3;
	private JYAlarmType alarmType4;

	public JYAlarmType getAlarmType4() {
		return alarmType4;
	}
	public void setAlarmType4(JYAlarmType alarmType4) {
		this.alarmType4 = alarmType4;
	}
	public JYAlarmType getAlarmType1() {
		return alarmType1;
	}
	public void setAlarmType1(JYAlarmType alarmType1) {
		this.alarmType1 = alarmType1;
	}
	public JYAlarmType getAlarmType2() {
		return alarmType2;
	}
	public void setAlarmType2(JYAlarmType alarmType2) {
		this.alarmType2 = alarmType2;
	}
	public JYAlarmType getAlarmType3() {
		return alarmType3;
	}
	public void setAlarmType3(JYAlarmType alarmType3) {
		this.alarmType3 = alarmType3;
	}
	public void setAlarmTypeService(JYAlarmTypeService alarmTypeService) {
		this.alarmTypeService = alarmTypeService;
	}
	public void updateAlarmTypeAction(){
		JYAlarmType alarmType11 = alarmTypeService.findJYAlarmTypeById("-11000");
		alarmType11.setEnable(alarmType1.getEnable());
		alarmType11.setValue(alarmType1.getValue());
		alarmTypeService.updateJYAlarmType(alarmType11);
		JYAlarmType alarmType12 = alarmTypeService.findJYAlarmTypeById("-11001");
		alarmType12.setEnable(alarmType2.getEnable());
		alarmType12.setValue(alarmType2.getValue());
		alarmTypeService.updateJYAlarmType(alarmType12);
		JYAlarmType alarmType13 = alarmTypeService.findJYAlarmTypeById("-11002");
		alarmType13.setEnable(alarmType3.getEnable());
		alarmType13.setValue(alarmType3.getValue());
		alarmTypeService.updateJYAlarmType(alarmType13);
		JYAlarmType alarmType14 = alarmTypeService.findJYAlarmTypeById("-11003");
		alarmType14.setEnable(alarmType4.getEnable());
		alarmType14.setValue(alarmType4.getValue());
		alarmType14.setSubValue(alarmType4.getSubValue());
		alarmTypeService.updateJYAlarmType(alarmType14);
		Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("data", 1);
        Constant.flush(dataMap);
	}
	public String showAlarmTypeAction(){
		alarmType1 = this.alarmTypeService.findJYAlarmTypeById("-11000");
		alarmType2 = this.alarmTypeService.findJYAlarmTypeById("-11001");
		alarmType3 = this.alarmTypeService.findJYAlarmTypeById("-11002");
		alarmType4 = this.alarmTypeService.findJYAlarmTypeById("-11003");
		return SUCCESS;
	}
}
