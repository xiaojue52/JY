package com.station.system.action;

import com.opensymphony.xwork2.ActionSupport;
import com.station.po.JYAlarmType;
import com.station.service.JYAlarmTypeService;

@SuppressWarnings("serial")
public class AlarmTypeAction extends ActionSupport{
	private JYAlarmTypeService alarmTypeService;
	private JYAlarmType alarmType1;
	private JYAlarmType alarmType2;
	private JYAlarmType alarmType3;

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
	public String updateAlarmTypeAction(){
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
		return SUCCESS;
	}
	public String showAlarmTypeAction(){
		alarmType1 = this.alarmTypeService.findJYAlarmTypeById("-11000");
		alarmType2 = this.alarmTypeService.findJYAlarmTypeById("-11001");
		alarmType3 = this.alarmTypeService.findJYAlarmTypeById("-11002");
		return SUCCESS;
	}
}
